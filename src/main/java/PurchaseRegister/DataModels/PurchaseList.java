package PurchaseRegister.DataModels;

import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * This class represents a purchase list with Purchase element type.
 * @see #PurchaseList()
 * @see #indexOf(int)
 * @see #get(int)
 * @see #add(LocalDate, Purchase.PurchaseType, double, String)
 * @see #modify(int, LocalDate, Purchase.PurchaseType, double, String)
 * @see #delete(int)
 * @see #count()
 * @see #clear()
 * @see #stream()
 * @author Laszlo Grimm
 */
public class PurchaseList {

	private final List<Purchase> purchaseList;

	public PurchaseList() {
		purchaseList = new ArrayList<>();
	}

	private int nextID() {
		OptionalInt optionalInt = purchaseList.stream()
				.map(Purchase::getPurchaseId)
				.mapToInt(id -> id)
				.max();
		return optionalInt.isEmpty() ? 0 : optionalInt.getAsInt() + 1;
	}

	public int indexOf(int id) {
		OptionalInt optionalInt = IntStream.range(0, purchaseList.size())
				.filter(n -> purchaseList.get(n).getPurchaseId() == id)
				.findFirst();
		return optionalInt.isEmpty() ? -1 : optionalInt.getAsInt();
	}

	/**
	 * In case the ID is not valid, returns null.
	 */
	public Purchase get(int id) {
		int itemIndex = indexOf(id);
		return itemIndex == -1 ? null : purchaseList.get(itemIndex);
	}

	/**
	 * Returns the ID of the added Purchase.
	 */
	public int add(LocalDate dateOfPurchase, Purchase.PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		int id = nextID();
		purchaseList.add(new Purchase(id, dateOfPurchase, typeOfPurchase, valueOfPurchase, descriptionOfPurchase));
		return id;
	}

	public boolean modify(int id, LocalDate dateOfPurchase, Purchase.PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		int itemIndex = indexOf(id);
		if (itemIndex == -1) {
			return false;
		}
		purchaseList.set(itemIndex, new Purchase(id, dateOfPurchase, typeOfPurchase, valueOfPurchase, descriptionOfPurchase));
		return true;
	}

	public boolean delete(int id) {
		int itemIndex = indexOf(id);
		if (itemIndex == -1) {
			return false;
		}
		purchaseList.remove(itemIndex);
		return true;
	}

	public int count() {
		return purchaseList.size();
	}

	public void clear() {
		purchaseList.clear();
	}

	public Stream<Purchase> stream() {
		return purchaseList.stream();
	}
}
