package PurchaseRegister.Storage;

import PurchaseRegister.DataModels.*;

/**
 * <b>This class provides data storage.</b><p>
 * All public methods are static.
 * @author Laszlo Grimm
 * @since 10-11-2022
 */
public class Data {

	public static final PurchaseList purchaseList = new PurchaseList();

	public static boolean load() {
		//TODO reload
		return true;
	}

	public static boolean save() {
		//TODO save
		return true;
	}
}
