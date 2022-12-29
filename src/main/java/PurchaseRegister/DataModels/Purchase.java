package PurchaseRegister.DataModels;

import org.springframework.format.annotation.*;
import org.springframework.lang.*;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.springframework.validation.annotation.*;

import java.time.*;
import java.util.*;

/**
 * This class represents a purchase.
 * @see #Purchase(long, LocalDate, PurchaseType, double, String)
 * @see #getPurchaseDate()
 * @see #getPurchaseType()
 * @see #getPurchaseValue()
 * @see #getPurchaseDescription()
 * @see #equals(Object)
 * @see #hashCode()
 * @author Laszlo Grimm
 */
@Validated
public class Purchase {

	public enum PurchaseType {
			CARD, CASH, INTERNET
	}
	@NotNull
	@Min(value = Long.MIN_VALUE + 1, message = "Need a whole number between " + (Long.MIN_VALUE + 1) + " and " + Long.MAX_VALUE + ".")
	@Max(value = Long.MAX_VALUE, message = "Need a whole number between " + (Long.MIN_VALUE + 1) + " and " + Long.MAX_VALUE + ".")
	private final Long purchaseId;
	@NotNull(message = "Need a legal date.")
	private final LocalDate purchaseDate;
	@NotNull(message = "Need a legal purchase type.")
	private final PurchaseType purchaseType;
	@NotNull(message = "Need a decimal number between " + Double.MIN_VALUE + " and " + Double.MAX_VALUE + ".")
	@DecimalMin(value = "4.9E-324", message = "Need a decimal number between " + Double.MIN_VALUE + " and " + Double.MAX_VALUE + ".")
	@DecimalMax(value = "1.7976931348623157E308", message = "Need a decimal number between " + Double.MIN_VALUE + " and " + Double.MAX_VALUE + ".")
	private final Double purchaseValue;
	@NotNull(message = "Need at least an empty text.")
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

	@Override
	public String toString() {
		return "Purchase{" +
				"purchaseId=" + purchaseId +
				", purchaseDate=" + purchaseDate +
				", purchaseType=" + purchaseType +
				", purchaseValue=" + purchaseValue +
				", purchaseDescription='" + purchaseDescription + '\'' +
				'}';
	}
}
