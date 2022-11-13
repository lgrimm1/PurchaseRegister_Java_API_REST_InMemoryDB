package PurchaseRegister.DataModels;

import java.time.*;
import java.util.*;

/**
 * <b>This class represents a statistics map which stores processed Stat data.</b>
 * @see #StatMap(StatType)
 * @see #getStatType()
 * @see #put(Purchase)
 * @see #get(LocalDate)
 * @see #count()
 * @author Laszlo Grimm
 * @since 13-11-2022
 */
public class StatMap {

	enum StatType {
		ANNUAL, MONTHLY
	}

	private final HashMap<LocalDate, Stat> statMap;
	private final StatType statType;

	public StatMap(StatType statType) {
		statMap = new HashMap<>();
		this.statType = statType;
	}

	public StatType getStatType() {
		return statType;
	}

	/**
	 * <b>Creates or upgrades statistical data, based on the given Purchase.</b><p>
	 * Converts purchase date following StatType of StatMap.<p>
	 * In case of null argument, returns false.
	 */
	public boolean put(Purchase newPurchase) {
		if (newPurchase == null) {
			return false;
		}
		LocalDate usedDate = switch (statType) {
			case ANNUAL -> usedDate = LocalDate.of(newPurchase.getPurchaseDate().getYear(), 1, 1);
			case MONTHLY -> usedDate = LocalDate.of(newPurchase.getPurchaseDate().getYear(), newPurchase.getPurchaseDate().getMonthValue(), 1);
		};
		if (statMap.containsKey(usedDate)) {
			Stat stat = statMap.get(usedDate);
			statMap.put(usedDate, new Stat(stat.getTotal() + newPurchase.getPurchaseValue(), stat.getCount() + 1));
		}
		else {
			statMap.put(usedDate, new Stat(newPurchase.getPurchaseValue(), 1));
		}
		return true;
	}

	/**
	 * <b>Returns statistical data for the given date.</b>
	 * Converts purchase date following StatType of StatMap.<p>
	 * In case the argument is null or no proper key, returns null.
	 */
	public Stat get(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		LocalDate usedDate = switch (statType) {
			case ANNUAL -> usedDate = LocalDate.of(localDate.getYear(), 1, 1);
			case MONTHLY -> usedDate = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 1);
		};
		if (statMap.containsKey(usedDate)) {
			return statMap.get(usedDate).deepCopy();
		}
		return null;
	}

	public int count() {
		return statMap.size();
	}
}
