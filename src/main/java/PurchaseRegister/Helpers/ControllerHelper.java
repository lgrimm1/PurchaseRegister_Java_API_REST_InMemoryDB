package PurchaseRegister.Helpers;

import PurchaseRegister.DataModels.*;

import java.time.*;
import java.util.*;

import static PurchaseRegister.Storage.Data.purchaseList;

/**
 * Helper class for PurchaseRegisterController class.
 * @author Laszlo Grimm
 */
public class ControllerHelper {

	public static Purchase getPurchaseByIdHelper(String idText) {
		if (idText == null) {
			return null;
		}
		int id;
		try {
			id = Integer.parseInt(idText);
		}
		catch (Exception e) {
			return null;
		}
		return purchaseList.get(id);
	}

	/**
	 * In case of failure, returns null.
	 */
	public static Integer addNewPurchaseHelper(LocalDate date, Purchase.PurchaseType type, Double value, String description) {
		if (date == null || type == null || value == null || description == null) {
			return null;
		}
		return purchaseList.add(date, type, value, description);
	}

	public static Boolean modifyPurchaseHelper(String idText, LocalDate date, Purchase.PurchaseType type, Double value, String description) {
		if (idText == null || date == null || type == null || value == null || description == null) {
			return false;
		}
		int id;
		try {
			id = Integer.parseInt(idText);
		}
		catch (Exception e) {
			return false;
		}
		return purchaseList.modify(id, date, type, value, description);
	}

	public static Boolean deletePurchaseHelper(String idText) {
		if (idText == null) {
			return false;
		}
		int id;
		try {
			id = Integer.parseInt(idText);
		}
		catch (Exception e) {
			return false;
		}
		return purchaseList.delete(id);
	}

	public static List<Purchase> getPurchasesHelper() {
		return purchaseList.stream().toList();
	}

	/**
	 * Returns IDs of deleted Purchases.
	 */
	public static List<Integer> deletePurchasesHelper(List<Integer> idList) {
		if (idList == null || idList.size() == 0) {
			return new ArrayList<>();
		}
		List<Integer> deleted = new ArrayList<>();
		for (Integer id : idList) {
			if (purchaseList.delete(id)) {
				deleted.add(id);
			}
		}
		return deleted;
	}

	public static List<StatAnnualTransfer> statAnnualHelper() {
		StatMap statMap = new StatMap(StatMap.StatType.ANNUAL);
		purchaseList.stream()
				.forEach(statMap::put);
		return statMap.stream()
				.map(entry -> new StatAnnualTransfer(entry.getKey().getYear(), entry.getValue().getTotal(), entry.getValue().getCount(), entry.getValue().getAverage()))
				.toList();
	}

	public static List<StatMonthlyTransfer> statMonthlyHelper() {
		StatMap statMap = new StatMap(StatMap.StatType.MONTHLY);
		purchaseList.stream()
				.forEach(statMap::put);
		return statMap.stream()
				.map(entry -> new StatMonthlyTransfer(entry.getKey().getYear(), entry.getKey().getMonthValue(), entry.getValue().getTotal(), entry.getValue().getCount(), entry.getValue().getAverage()))
				.toList();
	}
}
