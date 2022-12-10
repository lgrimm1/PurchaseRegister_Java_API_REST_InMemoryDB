package PurchaseRegister.DataModels;

import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * This class serves as statistical data collector, upgraded at every request on statistics.
 * @see #StatMap(StatType)
 * @see #getStatType()
 * @see #put(Purchase)
 * @see #get(LocalDate)
 * @see #count()
 * @see #stream()
 * @author Laszlo Grimm
 */
public class StatMap {

	public enum StatType {
		FULL, ANNUAL, MONTHLY
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
	 * Creates or upgrades statistical data.<p>
	 * Converts purchase date based on statType.<p>
	 * In case of null argument, returns false.
	 */
	public boolean put(Purchase newPurchase) {
		if (newPurchase == null) {
			return false;
		}
		LocalDate usedDate = switch (statType) {
			case FULL -> usedDate = LocalDate.of(2000, 1, 1);
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
	 * Converts date based on statType to find key.<p>
	 * In case the argument is null or no proper key, returns null.
	 */
	public Stat get(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		LocalDate usedDate = switch (statType) {
			case FULL -> usedDate = LocalDate.of(2000, 1, 1);
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

	/**
	 * Returns Stream&lt;Map.Entry&lt;LocalDate, Stat&gt;&gt;
	 */
	public Stream<Map.Entry<LocalDate, Stat>> stream() {
		return statMap.entrySet().stream();
	}
}
