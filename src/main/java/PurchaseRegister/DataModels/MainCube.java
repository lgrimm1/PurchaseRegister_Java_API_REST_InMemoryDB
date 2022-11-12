package PurchaseRegister.DataModels;

import PurchaseRegister.Helpers.*;

import java.util.*;

/**
 * <b>This class serves as data transfer storage of Main.html template.</b>
 * @see #MainCube(PurchaseList, List, String)
 * @see #getMainCubePurchaseLines()
 * @see #getMainCubeSelectionIndexes()
 * @see #getMessage()
 * @author Laszlo Grimm
 * @since 11-11-2022
 */
public class MainCube {

	private final List<String> mainCubePurchaseLines;
	private final List<Integer> mainCubeSelectionIndexes;
	private final String message;

	/**
	 * <b>Constructs a MainCube instance from list of purchases, selection indexes and a message.</b><p>
	 * In case the PurchaseList is null, no purchase lines will be created.<p>
	 * In case the list of given indexes is null or empty, no lines will be marked as selected.<p>
	 * In case the String is null, uses an empty String instead.
	 * @param purchaseList		List&lt;Purchase&gt; of purchases.
	 * @param selectionIndexes	List&lt;Integer&gt; of indexes of selected purchase lines.
	 * @param newMessage		String of message.
	 */
	public MainCube(PurchaseList purchaseList, List<Integer> selectionIndexes, String newMessage) {
		//TODO https://www.baeldung.com/thymeleaf-select-option
		//TODO https://stackoverflow.com/questions/54009330/how-to-display-selected-item-from-select-to-text-field-in-thymeleaf
		//selected index goes to a hidden input
		//TODO https://www.geeksforgeeks.org/html-dom-select-multiple-property/
		//TODO https://www.geeksforgeeks.org/html-dom-select-selectedindex-property/
		//select index from template
		//TODO https://www.tutorialspoint.com/How-to-show-the-index-of-the-selected-option-in-a-dropdown-list-with-JavaScript
		mainCubePurchaseLines = purchaseList == null ? new ArrayList<>() : purchaseList.stream()
				.map(purchase -> GeneralHelpers.purchaseToLine(purchase, " | "))
				.toList();
		mainCubeSelectionIndexes = selectionIndexes == null ? new ArrayList<>() : selectionIndexes;
		message = newMessage == null ? "" : newMessage;
	}

	/**
	 * <b>Returns the purchase lines.</b>
	 * @return	List&lt;String&gt; of purchase lines.
	 */
	public List<String> getMainCubePurchaseLines() {
		return mainCubePurchaseLines;
	}

	/**
	 * <b>Returns the indexes of selected purchase lines.</b>
	 * @return	List&lt;Integer&gt; of indexes of selected purchase lines.
	 */
	public List<Integer> getMainCubeSelectionIndexes() {
		return mainCubeSelectionIndexes;
	}

	/**
	 * <b>Returns the stored message.</b>
	 * @return	String of message.
	 */
	public String getMessage() {
		return message;
	}
}
