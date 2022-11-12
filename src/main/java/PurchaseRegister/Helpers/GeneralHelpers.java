package PurchaseRegister.Helpers;

import PurchaseRegister.DataModels.*;

import java.time.*;

/**
 * <b>This class provides general helper methods.</b><p>
 * All public methods are static.
 * @see #correctDateComponents(int, int, int)
 * @see #multiLineToSingleLine(String, String)
 * @see #purchaseToLine(Purchase, String)
 * @author Laszlo Grimm
 * @since 08-11-2022
 */
public class GeneralHelpers {

	/**
	 * <b>Returns a valid date based on given date components.</b><p>
	 * In case components are not valid, returns current local date.
	 * @param yearOfPurchase	int of year component.
	 * @param monthOfPurchase	int of month component.
	 * @param dayOfPurchase		int of day component.
	 * @return					LocalDate of valid date..
	 */
	public static LocalDate correctDateComponents(int yearOfPurchase, int monthOfPurchase, int dayOfPurchase) {
		LocalDate localDate;
		try {
			localDate = LocalDate.of(yearOfPurchase, monthOfPurchase, dayOfPurchase);
		}
		catch (Exception e) {
			localDate = LocalDate.now();
		}
		return localDate;
	}

	/**
	 * <b>Changes line breaks to separator text.</b><p>
	 * In case any argument is null, returns null.
	 * @param multiLine	String of multi-line text.
	 * @return			String of single-line text.
	 */
	public static String multiLineToSingleLine(String multiLine, String separator) {
		if (multiLine == null || separator == null) {
			return null;
		}
		return multiLine.replaceAll("\n", separator);
	}

	/**
	 * <b>Converts a Purchase into a String.</b><p>
	 * Uses - separator for date components, double space as separator between fields and the given separator for line breaks in purchase description.<p>
	 * In case any argument is null, returns null.
	 * @param purchase	Purchase of purchase.
	 * @param separator	String of new separator of multi-line text.
	 * @return			String of converted Purchase.
	 */
	public static String purchaseToLine(Purchase purchase, String separator) {
		if (purchase == null || separator == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append(String.format("%04d", purchase.getPurchaseDate().getYear()))
				.append("-")
				.append(String.format("%02d", purchase.getPurchaseDate().getMonthValue()))
				.append("-")
				.append(String.format("%02d", purchase.getPurchaseDate().getDayOfMonth()))
				.append("  ")
				.append(purchase.getPurchaseTypeString())
				.append("  ")
				.append(String.format("%.2f", purchase.getPurchaseValue()))
				.append("  ")
				.append(multiLineToSingleLine(purchase.getPurchaseDescription(), separator));
		return stringBuilder.toString();
	}
}
