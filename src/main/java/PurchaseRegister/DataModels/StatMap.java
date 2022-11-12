package PurchaseRegister.DataModels;

import java.time.*;
import java.util.*;

/**
 * <b>This class represents a statistics map which stores processed Stat data.</b>
 * @see #StatMap()
 * @see #put(Purchase)
 * @see #get(LocalDate)
 * @see #count()
 * @author Laszlo Grimm
 * @since 06-11-2022
 */
public class StatMap {

	private HashMap<LocalDate, Stat> statMap;

	/**
	 * <b>Constructs an instance of StatMap.</b>
	 */
	public StatMap() {
		statMap = new HashMap<>();
	}

	/**
	 * <b>Creates a new or modifies an existing statistical data, based on the given Purchase, identified by date.</b><p>
	 * In case of null argument, returns false.
	 * @param newPurchase	Purchase of data to process.
	 * @return				boolean of successfulness.
	 */
	public boolean put(Purchase newPurchase) {
		if (newPurchase == null) {
			return false;
		}
		Stat stat;
		if (statMap.containsKey(newPurchase.getPurchaseDate())) {
			stat = statMap.get(newPurchase.getPurchaseDate());
			stat.addTotal(newPurchase.getPurchaseValue());
		}
		else {
			stat = new Stat(newPurchase.getPurchaseValue());
		}
		statMap.put(newPurchase.getPurchaseDate(), stat);
		return true;
	}

	/**
	 * <b>Returns statistical data for the given date.</b>
	 * In case the argument is null, returns null.
	 * @param localDate	LocalDate of the key.
	 * @return			Stat of corresponding statistical data.
	 */
	public Stat get(LocalDate localDate) {
		return statMap.get(localDate);
	}

	/**
	 * <b>Returns the number of stored statistical data.</b>
	 * @return	int of stored data.
	 */
	public int count() {
		return statMap.size();
	}
}
