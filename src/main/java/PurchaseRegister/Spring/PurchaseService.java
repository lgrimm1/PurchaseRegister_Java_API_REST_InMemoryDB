package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.springframework.beans.factory.annotation.*;
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
 * @see #generateFullStat()
 * @author Laszlo Grimm
 */
@Service
public class PurchaseService {

	private final PurchaseStorage purchaseStorage;

	@Autowired
	public PurchaseService(PurchaseStorage purchaseStorage) {
		this.purchaseStorage = purchaseStorage;
	}

	public Purchase getPurchaseById(long id) {
		return purchaseStorage.get(id);
	}

	/**
	 * The registered new Purchase will get a legal ID in storage therefore the method ignores id field value of argument.<p>
	 * In case of any invalid data in the passed object, returns Long.MIN_Value.
	 */
	public long addNewPurchase(Purchase newPurchase) {
		if (newPurchase == null) {
			return Long.MIN_VALUE;
		}
		return purchaseStorage.add(
				newPurchase.getPurchaseDate(),
				newPurchase.getPurchaseType(),
				newPurchase.getPurchaseValue(),
				newPurchase.getPurchaseDescription());
	}

	public boolean modifyPurchase(Purchase newPurchase) {
		if (newPurchase == null) {
			return false;
		}
		return purchaseStorage.modify(
				newPurchase.getPurchaseId(),
				newPurchase.getPurchaseDate(),
				newPurchase.getPurchaseType(),
				newPurchase.getPurchaseValue(),
				newPurchase.getPurchaseDescription());
	}

	public Boolean deletePurchase(long id) {
		return purchaseStorage.delete(id);
	}

	public List<Purchase> getPurchases() {
		return purchaseStorage.stream().toList();
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
			if (purchaseStorage.delete(id)) {
				deletedIds.add(id);
			}
		}
		return deletedIds;
	}

	public int countPurchases() {
		return purchaseStorage.count();
	}

	public List<StatAnnualTransfer> generateAnnualStat() {
		StatMap statMap = new StatMap(StatMap.StatType.ANNUAL);
		purchaseStorage.stream()
				.forEach(statMap::put);
		return statMap.stream()
				.map(entry -> new StatAnnualTransfer(
						entry.getKey().getYear(),
						entry.getValue().getTotal(),
						entry.getValue().getCount(),
						entry.getValue().getAverage()))
				.toList();
	}

	public List<StatMonthlyTransfer> generateMonthlyStat() {
		StatMap statMap = new StatMap(StatMap.StatType.MONTHLY);
		purchaseStorage.stream()
				.forEach(statMap::put);
		return statMap.stream()
				.map(entry -> new StatMonthlyTransfer(
						entry.getKey().getYear(),
						entry.getKey().getMonthValue(),
						entry.getValue().getTotal(),
						entry.getValue().getCount(),
						entry.getValue().getAverage()))
				.toList();
	}

	public StatFullTransfer generateFullStat() {
		StatMap statMap = new StatMap(StatMap.StatType.FULL);
		purchaseStorage.stream()
				.forEach(statMap::put);
		return statMap.stream()
				.map(entry -> new StatFullTransfer(
						entry.getValue().getTotal(),
						entry.getValue().getCount(),
						entry.getValue().getAverage()))
				.toList().get(0);
	}
}
