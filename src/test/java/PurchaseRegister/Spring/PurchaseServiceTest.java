package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;

class PurchaseServiceTest {
	
	PurchaseStorage storage;
	PurchaseService service;
	long[] ids = new long[6];

	@BeforeEach
	void setUp() {
		storage = new PurchaseStorage();
		ids[0] = storage.add(LocalDate.of(2000, 5, 6), Purchase.PurchaseType.CARD, 12d, "abc");
		ids[1] = storage.add(LocalDate.of(2002, 3, 8), Purchase.PurchaseType.INTERNET, 36d, "ooo");
		ids[2] = storage.add(LocalDate.of(2002, 7, 4), Purchase.PurchaseType.INTERNET, 72d, "ooo");
		ids[3] = storage.add(LocalDate.of(2003, 3, 9), Purchase.PurchaseType.CARD, 48d, "123");
		ids[4] = storage.add(LocalDate.of(2003, 8, 3), Purchase.PurchaseType.CARD, 100d, "123");
		ids[5] = storage.add(LocalDate.of(2003, 8, 5), Purchase.PurchaseType.CARD, 35d, "123");
		service = new PurchaseService(storage);
	}

	@Test
	void getPurchaseWrongId() {
		Assertions.assertNull(service.getPurchaseById(64L));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void getPurchaseRightId() {
		Assertions.assertEquals(storage.get(Long.MIN_VALUE + 1), service.getPurchaseById(Long.MIN_VALUE + 1));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void addNullPurchase() {
		Assertions.assertNull(service.addNewPurchase(null));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void addPurchaseNullDate() {
		Assertions.assertNull(service.addNewPurchase(new Purchase(1, null, Purchase.PurchaseType.CASH, 12.3d, "abc")));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void addPurchaseNullType() {
		Assertions.assertNull(service.addNewPurchase(new Purchase(1, LocalDate.now(), null, 12.3d, "abc")));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void addPurchaseNullDescription() {
		Assertions.assertNull(service.addNewPurchase(new Purchase(1, LocalDate.now(), Purchase.PurchaseType.CASH, 12.3d, null)));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void addPurchaseRightArguments() {
		long id = service.addNewPurchase(new Purchase(1, LocalDate.now(), Purchase.PurchaseType.CASH, 112.3d, "abc"));
		Assertions.assertEquals(ids[5] + 1, id);
		Assertions.assertEquals(7, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());

		Assertions.assertEquals(LocalDate.now(), storage.get(id).getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, storage.get(id).getPurchaseType());
		Assertions.assertEquals(112.3d, storage.get(id).getPurchaseValue());
		Assertions.assertEquals("abc", storage.get(id).getPurchaseDescription());
	}

	@Test
	void modifyNullPurchase() {
		Assertions.assertFalse(service.modifyPurchase(null));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void modifyPurchaseNullDate() {
		Assertions.assertFalse(service.modifyPurchase(new Purchase(ids[3], null, Purchase.PurchaseType.CASH, 112.3d, "abc")));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());

		Assertions.assertEquals(LocalDate.of(2003, 3, 9), storage.get(ids[3]).getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, storage.get(ids[3]).getPurchaseType());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals("123", storage.get(ids[3]).getPurchaseDescription());

		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void modifyPurchaseNullType() {
		Assertions.assertFalse(service.modifyPurchase(new Purchase(3, LocalDate.now(), null, 112.3d, "abc")));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());

		Assertions.assertEquals(LocalDate.of(2003, 3, 9), storage.get(ids[3]).getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, storage.get(ids[3]).getPurchaseType());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals("123", storage.get(ids[3]).getPurchaseDescription());

		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void modifyPurchaseNullDescription() {
		Assertions.assertFalse(service.modifyPurchase(new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CASH, 112.3d, null)));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());

		Assertions.assertEquals(LocalDate.of(2003, 3, 9), storage.get(ids[3]).getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, storage.get(ids[3]).getPurchaseType());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals("123", storage.get(ids[3]).getPurchaseDescription());

		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void modifyPurchaseWrongId() {
		Assertions.assertFalse(service.modifyPurchase(new Purchase(64, LocalDate.now(), Purchase.PurchaseType.CASH, 112.3d, "ooo")));

		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void modifyPurchaseRightArguments() {
		Assertions.assertTrue(service.modifyPurchase(new Purchase(ids[1], LocalDate.now(), Purchase.PurchaseType.INTERNET, 111.1d, "XXX")));
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());

		Assertions.assertEquals(LocalDate.now(), storage.get(ids[1]).getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, storage.get(ids[1]).getPurchaseType());
		Assertions.assertEquals(111.1d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals("XXX", storage.get(ids[1]).getPurchaseDescription());

		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void deletePurchaseWrongId() {
		Assertions.assertFalse(service.deletePurchase(64));

		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void deletePurchaseRightId() {
		Assertions.assertTrue(service.deletePurchase(ids[1]));
		Assertions.assertEquals(5, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void getAllPurchases() {
		List<Purchase> list = service.getPurchases();
		Assertions.assertEquals(storage.count(), list.size());
		Assertions.assertEquals(storage.get(ids[0]), list.get(0));
		Assertions.assertEquals(storage.get(ids[1]), list.get(1));
		Assertions.assertEquals(storage.get(ids[2]), list.get(2));
		Assertions.assertEquals(storage.get(ids[3]), list.get(3));
		Assertions.assertEquals(storage.get(ids[4]), list.get(4));
		Assertions.assertEquals(storage.get(ids[5]), list.get(5));
	}

	@Test
	void deleteManyPurchasesNullList() {
		Assertions.assertEquals(0, service.deletePurchases(null).size());
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void deleteManyPurchasesEmptyList() {
		Assertions.assertEquals(0, service.deletePurchases(new ArrayList<>()).size());
		Assertions.assertEquals(6, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(36d, storage.get(ids[1]).getPurchaseValue());
		Assertions.assertEquals(72d, storage.get(ids[2]).getPurchaseValue());
		Assertions.assertEquals(48d, storage.get(ids[3]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void deleteManyPurchasesRightList() {
		Assertions.assertEquals(List.of(ids[1], ids[2], ids[3]), service.deletePurchases(List.of(ids[1], ids[2], ids[3], 32L, 64L)));
		Assertions.assertEquals(3, storage.count());
		Assertions.assertEquals(12d, storage.get(ids[0]).getPurchaseValue());
		Assertions.assertEquals(100d, storage.get(ids[4]).getPurchaseValue());
		Assertions.assertEquals(35d, storage.get(ids[5]).getPurchaseValue());
	}

	@Test
	void countPurchases() {
		Assertions.assertEquals(service.countPurchases(), storage.count());
		service.deletePurchase(ids[1]);
		Assertions.assertEquals(service.countPurchases(), storage.count());
	}

	@Test
	void statAnnual() {
		List<StatAnnualTransfer> list = service.generateAnnualStat();
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
		List<StatMonthlyTransfer> list = service.generateMonthlyStat();
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