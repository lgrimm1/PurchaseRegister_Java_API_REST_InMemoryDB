package PurchaseRegister.DataModels;

import java.time.*;
import java.util.*;

/**
 * This class represents a purchase.
 * @author Laszlo Grimm
 */
public class Purchase {

	public enum PurchaseType {
			CARD, CASH, INTERNET
	}
	private final Long purchaseId;
	private final LocalDate purchaseDate;
	private final PurchaseType purchaseType;
	private final Double purchaseValue;
	private final String purchaseDescription;

	public Purchase(long id, LocalDate dateOfPurchase, PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		purchaseId = id;
		purchaseDate = dateOfPurchase;
		purchaseType = typeOfPurchase;
		purchaseValue = valueOfPurchase;
		purchaseDescription = descriptionOfPurchase;
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public PurchaseType getPurchaseType() {
		return purchaseType;
	}

	public double getPurchaseValue() {
		return purchaseValue;
	}

	public String getPurchaseDescription() {
		return purchaseDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Purchase purchase = (Purchase) o;
		return Objects.equals(purchaseId, purchase.purchaseId) && Objects.equals(purchaseDate, purchase.purchaseDate) && purchaseType == purchase.purchaseType && Objects.equals(purchaseValue, purchase.purchaseValue) && Objects.equals(purchaseDescription, purchase.purchaseDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchaseId, purchaseDate, purchaseType, purchaseValue, purchaseDescription);
	}
}
