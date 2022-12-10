package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * Storage class of PurchaseService class.
 * @see #PurchaseStorage()
 * @see #nextID()
 * @see #indexOf(long)
 * @see #get(long)
 * @see #add(LocalDate, Purchase.PurchaseType, double, String)
 * @see #modify(long, LocalDate, Purchase.PurchaseType, double, String)
 * @see #delete(long)
 * @see #count()
 * @see #clear()
 * @see #stream()
 * @see #getMinimumId()
 * @see #getMaximumId()
 * @author Laszlo Grimm
 */
@Repository
public class PurchaseStorage {

	private final List<Purchase> purchaseList;
	private Long actualGreatestId;

	public PurchaseStorage() {
		purchaseList = new ArrayList<>();
		actualGreatestId = null;
	}

	private Long nextID() {
		if (actualGreatestId == null) {
			return Long.MIN_VALUE;
		}
		if (actualGreatestId == Long.MAX_VALUE) {
			return null;
		}
		return actualGreatestId + 1;
	}

	private int indexOf(long id) {
		OptionalInt optionalInt = IntStream.range(0, purchaseList.size())
				.filter(n -> purchaseList.get(n).getPurchaseId() == id)
				.findFirst();
		return optionalInt.isEmpty() ? -1 : optionalInt.getAsInt();
	}

	/**
	 * In case the ID is not valid, returns null.
	 */
	public Purchase get(long id) {
		int itemIndex = indexOf(id);
		return itemIndex == -1 ? null : purchaseList.get(itemIndex);
	}

	/**
	 * Returns the ID of the added Purchase.<p>
	 * In case Purchase arguments are not proper, returns null.
	 */
	public Long add(LocalDate dateOfPurchase, Purchase.PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		if (dateOfPurchase == null || typeOfPurchase == null || descriptionOfPurchase == null) {
			return null;
		}
		Long nextIdCandidate = nextID();
		if (nextIdCandidate == null) {
			return null;
		}
		actualGreatestId = nextIdCandidate;
		purchaseList.add(new Purchase(actualGreatestId, dateOfPurchase, typeOfPurchase, valueOfPurchase, descriptionOfPurchase));
		return actualGreatestId;
	}

	public boolean modify(long id, LocalDate dateOfPurchase, Purchase.PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		if (dateOfPurchase == null || typeOfPurchase == null || descriptionOfPurchase == null) {
			return false;
		}
		int itemIndex = indexOf(id);
		if (itemIndex == -1) {
			return false;
		}
		purchaseList.set(itemIndex, new Purchase(id, dateOfPurchase, typeOfPurchase, valueOfPurchase, descriptionOfPurchase));
		return true;
	}

	public boolean delete(long id) {
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

	public long getMinimumId() {
		return Long.MIN_VALUE;
	}

	public long getMaximumId() {
		return Long.MAX_VALUE;
	}
}
