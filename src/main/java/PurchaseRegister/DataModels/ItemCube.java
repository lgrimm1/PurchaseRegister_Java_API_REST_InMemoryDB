package PurchaseRegister.DataModels;

import java.time.*;

/**
 * <b>This class serves as data transfer storage of Item.html template.</b>
 * @see #ItemCube(int, Purchase, String)
 * @see #getItemIndex()
 * @see #getPurchaseDate()
 * @see #getPurchaseType()
 * @see #getPurchaseValue()
 * @see #getPurchaseDescription()
 * @see #getMessage()
 * @author Laszlo Grimm
 * @since 10-11-2022
 */
public class ItemCube {
	private final Integer itemIndex;
	private final LocalDate purchaseDate;
	private final Purchase.PurchaseType purchaseType;
	private final Double purchaseValue;
	private final String purchaseDescription;
	private final String message;

	/**
	 * <b>Constructs a new ItemCube instance.</b><p>
	 * In case the Purchase is null, constructs a Purchase with default values.<p>
	 * In case the message is null, uses an empty String.
	 * @param itemIndex		int of PurchaseList item index.
	 * @param purchase		Purchase of purchase.
	 * @param newMessage	String of message whixh will be used on Item template.
	 */
	public ItemCube(int itemIndex, Purchase purchase, String newMessage) {
		if (purchase == null) {
			purchase = new Purchase(LocalDate.now(), Purchase.PurchaseType.CARD, 0d, "");
		}
		this.itemIndex = itemIndex;
		this.purchaseDate = purchase.getPurchaseDate();
		this.purchaseType = purchase.getPurchaseType();
		this.purchaseValue = purchase.getPurchaseValue();
		this.purchaseDescription = purchase.getPurchaseDescription();
		this.message = newMessage == null? "" : newMessage;
	}

	/**
	 * <b>Returns the item index.</b>
	 * @return	Integer of the item index.
	 */
	public Integer getItemIndex() {
		return itemIndex;
	}

	/**
	 * <b>Returns the date of purchase.</b>
	 * @return LocalDate of date.
	 */
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * <b>Returns the purchase type.</b>
	 * @return	PurchaseType of the type.
	 */
	public Purchase.PurchaseType getPurchaseType() {
		return purchaseType;
	}

	/**
	 * <b>Returns the purchase value.</b>
	 * @return	Double of purchase value.
	 */
	public Double getPurchaseValue() {
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
	 * <b>Returns the message attached to the item.</b>
	 * @return	String of message.
	 */
	public String getMessage() {
		return message;
	}
}
