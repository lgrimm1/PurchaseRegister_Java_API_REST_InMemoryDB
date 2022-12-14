package PurchaseRegister.DataModels;

import java.io.*;
import java.math.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * This class represents a purchase.
 * @see #Purchase(Long, LocalDate, PurchaseType, BigDecimal, String)
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
	private final Long purchaseId;
	transient private LocalDate purchaseDate;
	private final PurchaseType purchaseType;
	private final BigDecimal purchaseValue;
	//TODO knowledge:
	// The JSON parser of Spring (which converts from JSON to serialized object) evaluates numbers in JSON as Long for whole numbers and BigDecimal for float numbers.
	// Although the BigDecimal has proper automatism for serialization and deserialization, does not have automatism to convert to wrapper or primitive type of float numbers.
	// Due to these, the receiver object can contain Long, Integer, long and int types for whole numbers, but for float numbers in object, always use BigDecimal as type.
	//TODO knowledge: number types sent to the Spring output has default value of zero. That said, in case Integer, Long or BigDecimal as return value in controller method signature, when null is returned, zero will be sent. If that is not acceptable, return a well-designed number instead of null.
	//TODO knowledge: PurchaseStorage#indexOf(): INDEXOF WITH STREAM, Practices.txt
	//TODO knowledge: when(mock.isOk()).thenReturn(true); when(mock.isOk()).thenThrow(exception); doThrow(exception).when(mock).someVoidMethod();, Mockito.txt

	private final String purchaseDescription;

	public Purchase(Long id, LocalDate dateOfPurchase, PurchaseType typeOfPurchase, BigDecimal valueOfPurchase, String descriptionOfPurchase) {
		purchaseId = id;
		purchaseDate = dateOfPurchase;
		purchaseType = typeOfPurchase;
		purchaseValue = valueOfPurchase;
		purchaseDescription = descriptionOfPurchase;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public PurchaseType getPurchaseType() {
		return purchaseType;
	}

	public BigDecimal getPurchaseValue() {
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
