package PurchaseRegister.Helpers;

import PurchaseRegister.DataModels.*;
import org.springframework.ui.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

import static PurchaseRegister.Storage.Data.purchaseList;

/**
 * Helper class for PurchaseRegisterController class.
 * @author Laszlo Grimm
 */
public class ControllerHelper {

	public static final String noSuchPurchasePage = "noSuchPurchasePage";
	public static final String purchasePage = "purchasePage";
	public static final String unsuccessfulNewPurchasePage = "unsuccessfulNewPurchasePage";
	public static final String successfulNewPurchasePage = "successfulNewPurchasePage";
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

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

	/**
	 * In case of wrong input, returns null.
	 */
	private static LocalDate dateTextToDate(String dateText) {
		if (dateText == null) {
			return null;
		}
		try {
			return LocalDate.parse(dateText, dateTimeFormatter);
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * In case of wrong input, returns null.
	 */
	private static Purchase.PurchaseType typeTextToType(String typeText) {
		if (typeText == null || typeText.isEmpty()) {
			return null;
		}
		return switch (typeText) {
			case "CARD" -> Purchase.PurchaseType.CARD;
			case "CASH" -> Purchase.PurchaseType.CASH;
			case "INTERNET" -> Purchase.PurchaseType.INTERNET;
			default -> null;
		};
	}
}
