package PurchaseRegister.DataModels;

import PurchaseRegister.Helpers.*;

import java.time.*;

/**
 * <b>This class represents a purchase.</b>
 * @see #Purchase(LocalDate, PurchaseType, double, String)
 * @see #Purchase(int, int, int, PurchaseType, double, String)
 * @see #getPurchaseDate()
 * @see #getPurchaseType()
 * @see #getPurchaseValue()
 * @see #getPurchaseDescription()
 * @see #getPurchaseTypeString()
 * @see #compareTo(Purchase)
 * @author Laszlo Grimm
 * @since 10-11-2022
 */
public class Purchase implements Comparable<Purchase> {

	public enum PurchaseType {
			CARD, CASH, INTERNET
	}
	private final LocalDate purchaseDate;
	private final PurchaseType purchaseType;
	private final double purchaseValue;
	private final String purchaseDescription;

	/**
	 * <b>Constructs an instance with the given date.</b><p>
	 * In case the given date is null, uses current local date.<p>
	 * In case the given description is null, uses empty String.
	 * @param dateOfPurchase		LocalDate of purchase.
	 * @param typeOfPurchase		PurchaseType of type of purchase.
	 * @param valueOfPurchase		double of value of purchase.
	 * @param descriptionOfPurchase	String of purchase description.
	 */
	public Purchase(LocalDate dateOfPurchase, PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		purchaseDate = dateOfPurchase == null ? LocalDate.now() : dateOfPurchase;
		purchaseType = typeOfPurchase;
		purchaseValue = valueOfPurchase;
		purchaseDescription = descriptionOfPurchase == null ? "" : descriptionOfPurchase;
	}

	/**
	 * <b>Constructs an instance with the given date components.</b><p>
	 * In case the date components define no valid date, uses current date.<p>
	 * In case the given description is null, uses empty String.
	 * @param yearOfPurchase		int of year component.
	 * @param monthOfPurchase		int of month component.
	 * @param dayOfPurchase			int of day component.
	 * @param typeOfPurchase		PurchaseType of type of purchase.
	 * @param valueOfPurchase		double of value of purchase.
	 * @param descriptionOfPurchase	String of purchase description.
	 */
	public Purchase(int yearOfPurchase, int monthOfPurchase, int dayOfPurchase, PurchaseType typeOfPurchase, double valueOfPurchase, String descriptionOfPurchase) {
		this(GeneralHelpers.correctDateComponents(yearOfPurchase, monthOfPurchase, dayOfPurchase), typeOfPurchase, valueOfPurchase, descriptionOfPurchase);
	}

	/**
	 * <b>Returns the date as a LocalDate.</b>
	 * @return	Date of date.
	 */
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * <b>Returns the type.</b>
	 * @return	PurchaseType of type.
	 */
	public PurchaseType getPurchaseType() {
		return purchaseType;
	}

	/**
	 * <b>Returns the purchase value.</b>
	 * @return	double of value.
	 */
	public double getPurchaseValue() {
		return purchaseValue;
	}

	/**
	 * <b>Returns the description.</b>
	 * @return	String of description.
	 */
	public String getPurchaseDescription() {
		return purchaseDescription;
	}

	/**
	 * <b>Returns the purchase type as a String.</b>
	 * @return	String of purchase type.
	 */
	public String getPurchaseTypeString() {
		return switch (purchaseType) {
			case CARD -> "CARD";
			case CASH -> "CASH";
			case INTERNET -> "INET";
		};
	}

	/**
	 * <b>Compares this Purchase to an other Purchase based on their fields.</b>
	 * @param otherPurchase	Purchase to be compared.
	 * @return				int of result: -1 if this Purchase is lesser, +1 if greater than the other, 0 for equality.
	 */
	@Override
	public int compareTo(Purchase otherPurchase) {
		return GeneralHelpers.purchaseToLine(this, "|").compareTo(GeneralHelpers.purchaseToLine(otherPurchase, "|"));
	}
}
