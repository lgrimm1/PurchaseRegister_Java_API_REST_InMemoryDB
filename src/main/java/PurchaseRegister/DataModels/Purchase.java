package PurchaseRegister.DataModels;

import java.io.*;
import java.time.*;

/**
 * This class represents a purchase.
 * @see #Purchase(long, LocalDate, PurchaseType, double, String)
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
	private final long purchaseId;
	private final LocalDate purchaseDate;
	private final PurchaseType purchaseType;
	private final double purchaseValue;
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
}
