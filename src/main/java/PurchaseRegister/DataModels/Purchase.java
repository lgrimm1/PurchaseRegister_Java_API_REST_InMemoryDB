package PurchaseRegister.DataModels;

import java.io.*;
import java.time.*;
import java.time.format.*;

/**
 * This class represents a purchase.
 * @see #Purchase(long, LocalDate, PurchaseType, double, String)
 * @see #getPurchaseDate()
 * @see #getPurchaseType()
 * @see #getPurchaseValue()
 * @see #getPurchaseDescription()
 * @see #writeObject(ObjectOutputStream)
 * @see #readObject(ObjectInputStream)
 * @author Laszlo Grimm
 */
public class Purchase implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	transient private final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

	public enum PurchaseType {
			CARD, CASH, INTERNET
	}
	private final long purchaseId;
	transient private LocalDate purchaseDate;
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

	@Serial
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		String dateText = purchaseDate.format(dtf);
		oos.writeObject(dateText);
	}

	@Serial
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		String dateText = (String) ois.readObject();
		purchaseDate = LocalDate.parse(dateText, dtf);
	}
}
