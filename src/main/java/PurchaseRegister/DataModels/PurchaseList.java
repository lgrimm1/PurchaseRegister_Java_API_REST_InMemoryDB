package PurchaseRegister.DataModels;

import PurchaseRegister.Helpers.*;

import java.util.*;
import java.util.stream.*;

/**
 * <b>This class represents a purchase list with Purchase element type.</b>
 * @see #PurchaseList()
 * @see #get(int)
 * @see #add(Purchase)
 * @see #modify(int, Purchase)
 * @see #delete(int)
 * @see #count()
 * @see #clear()
 * @see #sort()
 * @see #stream()
 * @author Laszlo Grimm
 * @since 10-11-2022
 */
public class PurchaseList {

	/**
	 * <b>Comparator for ascended sorting of list of Purchases.</b>
	 */
	private final Comparator<Purchase> purchaseComparator = new Comparator<>() {
		@Override
		public int compare(Purchase element1, Purchase element2) {
			return GeneralHelpers.purchaseToLine(element1, "|").compareTo(GeneralHelpers.purchaseToLine(element2, "|"));
		}
	};

	private List<Purchase> purchaseList;

	/**
	 * <b>Constructs a new PurchaseList instance.</b>
	 */
	public PurchaseList() {
		purchaseList = new ArrayList<>();
	}

	/**
	 * <b>Returns a TPurchase element at the given index.</b><p>
	 * In case the index is not valid, returns null.
	 * @param itemIndex		int of element position.
	 * @return				Purchase at itemIndex.
	 */
	public Purchase get(int itemIndex) {
		if (itemIndex > -1 && itemIndex < purchaseList.size()) {
			return purchaseList.get(itemIndex);
		}
		return null;
	}

	/**
	 * <b>Adds a Purchase element.</b>
	 * @param newPurchase	Purchase of the new element.
	 * @return				boolean of successfulness.
	 */
	public boolean add(Purchase newPurchase) {
		return newPurchase != null && purchaseList.add(newPurchase);
	}

	/**
	 * <b>Modifies a Purchase element at the given index.</b>
	 * @param itemIndex		int of element position.
	 * @param newPurchase	Purchase of the new element.
	 * @return				boolean of successfulness.
	 */
	public boolean modify(int itemIndex, Purchase newPurchase) {
		if (itemIndex > -1 && itemIndex < purchaseList.size() && newPurchase != null) {
			purchaseList.set(itemIndex, newPurchase);
			return true;
		}
		return false;
	}

	/**
	 * <b>Deletes a Purchase element at the given index.</b>
	 * @param itemIndex		int of element position.
	 * @return				boolean of successfulness.
	 */
	public boolean delete(int itemIndex) {
		if (itemIndex > -1 && itemIndex < purchaseList.size()) {
			purchaseList.remove(itemIndex);
			return true;
		}
		return false;
	}

	/**
	 * <b>Returns the size of the PurchaseList.</b>
	 * @return	int of the list size.
	 */
	public int count() {
		return purchaseList.size();
	}

	/**
	 * <b>Deletes all elements of the PurchaseList.</b>
	 */
	public void clear() {
		purchaseList.clear();
	}

	/**
	 * <b>Sorts the PurchaseList.</b>
	 */
	public void sort() {
		purchaseList.sort(purchaseComparator);
	}

	/**
	 * <b>Returns the PurchaseList as a stream.</b>
	 * @return	Stream&lt;Purchase&gt; of purchases.
	 */
	public Stream<Purchase> stream() {
		return purchaseList.stream();
	}
}
