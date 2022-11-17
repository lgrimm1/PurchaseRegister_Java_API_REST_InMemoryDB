package PurchaseRegister.Helpers;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;
import org.springframework.ui.*;

import java.time.*;
import java.util.*;

import static PurchaseRegister.Storage.Data.purchaseList;
import PurchaseRegister.DataModels.Stat;
import PurchaseRegister.DataModels.StatMap;

class ControllerHelperTest {

	@BeforeEach
	void setUp() {
		purchaseList.add(LocalDate.of(2000, 5, 6), Purchase.PurchaseType.CARD, 12d, "abc");
		purchaseList.add(LocalDate.of(2002, 3, 8), Purchase.PurchaseType.INTERNET, 36d, "ooo");
		purchaseList.add(LocalDate.of(2002, 7, 4), Purchase.PurchaseType.INTERNET, 72d, "ooo");
		purchaseList.add(LocalDate.of(2003, 3, 9), Purchase.PurchaseType.CARD, 48d, "123");
		purchaseList.add(LocalDate.of(2003, 8, 3), Purchase.PurchaseType.CARD, 100d, "123");
		purchaseList.add(LocalDate.of(2003, 8, 5), Purchase.PurchaseType.CARD, 35d, "123");
	}

	@AfterEach
	void tearDown() {
		purchaseList.clear();
	}

	@Test
	void getPurchaseNullId() {
		Assertions.assertNull(ControllerHelper.getPurchaseByIdHelper(null));
	}

	@Test
	void getPurchaseWrongId() {
		Assertions.assertNull(ControllerHelper.getPurchaseByIdHelper(String.valueOf(64)));
		Assertions.assertNull(ControllerHelper.getPurchaseByIdHelper("12.3"));
		Assertions.assertNull(ControllerHelper.getPurchaseByIdHelper("12,3"));
	}

	@Test
	void getPurchaseRightId() {
		Assertions.assertEquals(purchaseList.get(1), ControllerHelper.getPurchaseByIdHelper("1"));
	}

	@Test
	void addNullDate() {
		Assertions.assertNull(ControllerHelper.addNewPurchaseHelper(null, Purchase.PurchaseType.CASH, 12.3d, "abc"));
	}

	@Test
	void addNullType() {
		Assertions.assertNull(ControllerHelper.addNewPurchaseHelper(LocalDate.now(), null, 12.3d, "abc"));
	}

	@Test
	void addNullValue() {
		Assertions.assertNull(ControllerHelper.addNewPurchaseHelper(LocalDate.now(), Purchase.PurchaseType.CASH, null, "abc"));
	}

	@Test
	void addNullDescription() {
		Assertions.assertNull(ControllerHelper.addNewPurchaseHelper(LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, null));
	}

	@Test
	void addRightArguments() {
		Assertions.assertInstanceOf(Integer.class, ControllerHelper.addNewPurchaseHelper(LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, "abc"));
	}

	@Test
	void modifyNullId() {
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper(null, LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, "abc"));
	}

	@Test
	void modifyNullDate() {
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("3", null, Purchase.PurchaseType.CASH, 12.3d, "abc"));
	}

	@Test
	void modifyNullType() {
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("3", LocalDate.now(), null, 12.3d, "abc"));
	}

	@Test
	void modifyNullValue() {
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("3", LocalDate.now(), Purchase.PurchaseType.CASH, null, "abc"));
	}

	@Test
	void modifyNullDescription() {
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("3", LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, null));
	}

	@Test
	void modifyWrongId() {
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("", LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, "ooo"));
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("abc", LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, "ooo"));
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("3a4", LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, "ooo"));
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("12.3", LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, "ooo"));
		Assertions.assertFalse(ControllerHelper.modifyPurchaseHelper("12,3", LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, "ooo"));
	}

	@Test
	void modifyRightArguments() {
		Assertions.assertTrue(ControllerHelper.modifyPurchaseHelper("1", LocalDate.now(), Purchase.PurchaseType.INTERNET, 111.1d, "XXX"));
	}

	@Test
	void deleteNullId() {
		Assertions.assertFalse(ControllerHelper.deletePurchaseHelper(null));
	}

	@Test
	void deleteWrongId() {
		Assertions.assertFalse(ControllerHelper.deletePurchaseHelper(""));
		Assertions.assertFalse(ControllerHelper.deletePurchaseHelper("abc"));
		Assertions.assertFalse(ControllerHelper.deletePurchaseHelper("a3d"));
		Assertions.assertFalse(ControllerHelper.deletePurchaseHelper("12.3"));
		Assertions.assertFalse(ControllerHelper.deletePurchaseHelper("12,3"));
	}

	@Test
	void deleteRightId() {
		Assertions.assertTrue(ControllerHelper.deletePurchaseHelper("1"));
	}

	@Test
	void getAll() {
		Assertions.assertEquals(purchaseList.count(), ControllerHelper.getPurchasesHelper().size());
		Assertions.assertEquals(purchaseList.get(0), ControllerHelper.getPurchasesHelper().get(0));
	}

	@Test
	void deleteManyNullList() {
		Assertions.assertEquals(0, ControllerHelper.deletePurchasesHelper(null).size());
	}

	@Test
	void deleteManyEmptyList() {
		Assertions.assertEquals(0, ControllerHelper.deletePurchasesHelper(new ArrayList<>()).size());
	}

	@Test
	void deleteManyRightList() {
		Assertions.assertEquals(List.of(1, 2, 3), ControllerHelper.deletePurchasesHelper(List.of(1, 2, 3, 32, 64)));
	}

	@Test
	void statAnnual() {
		List<StatAnnualTransfer> list = ControllerHelper.statAnnualHelper();
		Assertions.assertEquals(3, list.size());

		Assertions.assertEquals(2000, list.get(0).getYear());
		Assertions.assertEquals(12d, list.get(0).getTotal());
		Assertions.assertEquals(1, list.get(0).getCount());
		Assertions.assertEquals(12d, list.get(0).getAverage());

		Assertions.assertEquals(2002, list.get(1).getYear());
		Assertions.assertEquals(108d, list.get(1).getTotal());
		Assertions.assertEquals(2, list.get(1).getCount());
		Assertions.assertEquals(54d, list.get(1).getAverage());

		Assertions.assertEquals(2003, list.get(2).getYear());
		Assertions.assertEquals(183d, list.get(2).getTotal());
		Assertions.assertEquals(3, list.get(2).getCount());
		Assertions.assertEquals(61d, list.get(2).getAverage());
	}

	@Test
	void statMonthly() {
		List<StatMonthlyTransfer> list = ControllerHelper.statMonthlyHelper();
		Assertions.assertEquals(5, list.size());

		Assertions.assertEquals(2000, list.get(0).getYear());
		Assertions.assertEquals(5, list.get(0).getMonth());
		Assertions.assertEquals(12d, list.get(0).getTotal());
		Assertions.assertEquals(1, list.get(0).getCount());
		Assertions.assertEquals(12d, list.get(0).getAverage());

		Assertions.assertEquals(2002, list.get(1).getYear());
		Assertions.assertEquals(3, list.get(1).getMonth());
		Assertions.assertEquals(36d, list.get(1).getTotal());
		Assertions.assertEquals(1, list.get(1).getCount());
		Assertions.assertEquals(36d, list.get(1).getAverage());

		Assertions.assertEquals(2002, list.get(2).getYear());
		Assertions.assertEquals(7, list.get(2).getMonth());
		Assertions.assertEquals(72d, list.get(2).getTotal());
		Assertions.assertEquals(1, list.get(2).getCount());
		Assertions.assertEquals(72d, list.get(2).getAverage());

		Assertions.assertEquals(2003, list.get(3).getYear());
		Assertions.assertEquals(3, list.get(3).getMonth());
		Assertions.assertEquals(48d, list.get(3).getTotal());
		Assertions.assertEquals(1, list.get(3).getCount());
		Assertions.assertEquals(48d, list.get(3).getAverage());

		Assertions.assertEquals(2003, list.get(4).getYear());
		Assertions.assertEquals(8, list.get(4).getMonth());
		Assertions.assertEquals(135d, list.get(4).getTotal());
		Assertions.assertEquals(2, list.get(4).getCount());
		Assertions.assertEquals(67.5d, list.get(4).getAverage());
	}
}