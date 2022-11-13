package PurchaseRegister.DataModels;

import PurchaseRegister.Helpers.*;

import java.time.*;

/**
 * <b>This class represents a purchase.</b>
 * @see #Purchase(int, LocalDate, PurchaseType, double, String)
 * @see #getPurchaseDate()
 * @see #getPurchaseType()
 * @see #getPurchaseValue()
 * @see #getPurchaseDescription()
// * @see #getPurchaseTypeString()
 * @author Laszlo Grimm
 * @since 13-11-2022
 */
//public class Purchase implements Comparable<Purchase> {
public class Purchase {

	public enum PurchaseType {
			CARD, CASH, INTERNET
	}
	private final int purchaseId;
	private final LocalDate purchaseDate;
	private final PurchaseType purchaseType;
	private final double purchaseValue;
	private final String purchaseDescription;

	/**
	 * <b>Constructs an instance with the given date.</b><p>
	 * In case the given date is null, uses current local date.<p>
	 * In case the given description is null, uses empty String.
	 */
	public Purchase(int id, LocalDate dateOfPurchase, PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		purchaseId = id;
		purchaseDate = dateOfPurchase == null ? LocalDate.now() : dateOfPurchase;
		purchaseType = typeOfPurchase;
		purchaseValue = valueOfPurchase;
		purchaseDescription = descriptionOfPurchase == null ? "" : descriptionOfPurchase;
	}

	public int getPurchaseId() {
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

/*
	public String getPurchaseTypeString() {
		return switch (purchaseType) {
			case CARD -> "CARD";
			case CASH -> "CASH";
			case INTERNET -> "INET";
		};
	}
*/
}
