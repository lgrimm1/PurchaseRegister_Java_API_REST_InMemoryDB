package PurchaseRegister.DataModels;

import java.io.*;
import java.time.*;

/**
 * This class represents a purchase.
 * @see #Purchase(int, LocalDate, PurchaseType, double, String)
 * @see #getPurchaseDate()
 * @see #getPurchaseType()
 * @see #getPurchaseValue()
 * @see #getPurchaseDescription()
 * @author Laszlo Grimm
 */
public class Purchase implements Serializable {

	public enum PurchaseType {
			CARD, CASH, INTERNET
	}
	private final int purchaseId;
	private final LocalDate purchaseDate;
	private final PurchaseType purchaseType;
	private final double purchaseValue;
	private final String purchaseDescription;

	/**
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
}
