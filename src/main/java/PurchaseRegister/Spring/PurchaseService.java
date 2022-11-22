package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Service class of PurchaseController class.
 * @see #PurchaseService(PurchaseStorage)
 * @see #getPurchaseById(long)
 * @see #addNewPurchase(Purchase)
 * @see #modifyPurchase(Purchase)
 * @see #deletePurchase(long)
 * @see #getPurchases()
 * @see #deletePurchases(List)
 * @see #countPurchases()
 * @see #generateAnnualStat()
 * @see #generateMonthlyStat()
// * @see #parseDate(String)
// * @see #parseType(String)
 * @author Laszlo Grimm
 */
@Service
public class PurchaseService {

	private final PurchaseStorage storage;

	public PurchaseService(PurchaseStorage storage) {
		this.storage = storage;
	}

	public Purchase getPurchaseById(long id) {
		return storage.get(id);
	}

	/**
	 * Ignores id field value.<p>
	 * In case of failure, returns null.
	 */
	public Long addNewPurchase(Purchase newPurchase) {
		if (newPurchase == null || newPurchase.getPurchaseDate() == null || newPurchase.getPurchaseType() == null || newPurchase.getPurchaseDescription() == null) {
			return null;
		}
		return storage.add(newPurchase.getPurchaseDate(), newPurchase.getPurchaseType(), newPurchase.getPurchaseValue(), newPurchase.getPurchaseDescription());
	}

	public boolean modifyPurchase(Purchase newPurchase) {
		if (newPurchase == null || newPurchase.getPurchaseDate() == null || newPurchase.getPurchaseType() == null || newPurchase.getPurchaseDescription() == null) {
			return false;
		}
		return storage.modify(newPurchase.getPurchaseId(), newPurchase.getPurchaseDate(), newPurchase.getPurchaseType(), newPurchase.getPurchaseValue(), newPurchase.getPurchaseDescription());
	}

	public Boolean deletePurchase(long id) {
		return storage.delete(id);
	}

	public List<Purchase> getPurchases() {
		return storage.stream().toList();
	}

	/**
	 * Returns IDs of deleted Purchases.
	 */
	public List<Long> deletePurchases(List<Long> idList) {
		if (idList == null || idList.size() == 0) {
			return new ArrayList<>();
		}
		List<Long> deletedIds = new ArrayList<>();
		for (Long id : idList) {
			if (storage.delete(id)) {
				deletedIds.add(id);
			}
		}
		return deletedIds;
	}

	public int countPurchases() {
		return storage.count();
	}

	public List<StatAnnualTransfer> generateAnnualStat() {
		StatMap statMap = new StatMap(StatMap.StatType.ANNUAL);
		storage.stream()
				.forEach(statMap::put);
		return statMap.stream()
				.map(entry -> new StatAnnualTransfer(entry.getKey().getYear(), entry.getValue().getTotal(), entry.getValue().getCount(), entry.getValue().getAverage()))
				.toList();
	}

	public List<StatMonthlyTransfer> generateMonthlyStat() {
		StatMap statMap = new StatMap(StatMap.StatType.MONTHLY);
		storage.stream()
				.forEach(statMap::put);
		return statMap.stream()
				.map(entry -> new StatMonthlyTransfer(entry.getKey().getYear(), entry.getKey().getMonthValue(), entry.getValue().getTotal(), entry.getValue().getCount(), entry.getValue().getAverage()))
				.toList();
	}
}
