package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;

class PurchaseListTest {

	@Test
	void empty() {
		PurchaseList pl = new PurchaseList();
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void  addPurchaseNullArguments() {
		PurchaseList pl = new PurchaseList();
		pl.add(null, Purchase.PurchaseType.CARD, 65.4d, null);
		Assertions.assertEquals(1, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(65.4d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void addCardPurchase() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.now(), Purchase.PurchaseType.CARD, 65.4d, "something");
		Assertions.assertEquals(1, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(65.4d, p.getPurchaseValue());
		Assertions.assertEquals("something", p.getPurchaseDescription());
	}

	@Test
	void addCashPurchase() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.now(), Purchase.PurchaseType.CASH, 65.4d, "something");
		Assertions.assertEquals(1, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(65.4d, p.getPurchaseValue());
		Assertions.assertEquals("something", p.getPurchaseDescription());
	}

	@Test
	void addInternetPurchase() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.now(), Purchase.PurchaseType.INTERNET, 65.4d, "something");
		Assertions.assertEquals(1, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(65.4d, p.getPurchaseValue());
		Assertions.assertEquals("something", p.getPurchaseDescription());
	}

	@Test
	void addMoreElements() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.of(2010, 3, 5), Purchase.PurchaseType.CARD, 10d, "abc");
		pl.add(LocalDate.of(2011, 4, 6), Purchase.PurchaseType.CASH, 20d, "xyz");
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(1);
		Assertions.assertEquals(1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20d, p.getPurchaseValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void  modifyPurchaseWrongId() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.of(2010, 3, 5), Purchase.PurchaseType.CARD, 10d, "abc");
		pl.add(LocalDate.of(2011, 4, 6), Purchase.PurchaseType.CASH, 20d, "xyz");

		Assertions.assertFalse(pl.modify(3, null, Purchase.PurchaseType.CARD, 65.4d, null));

		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(1);
		Assertions.assertEquals(1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20d, p.getPurchaseValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void  modifyPurchaseNullArguments() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.of(2010, 3, 5), Purchase.PurchaseType.CARD, 10d, "abc");
		pl.add(LocalDate.of(2011, 4, 6), Purchase.PurchaseType.CASH, 20d, "xyz");

		Assertions.assertTrue(pl.modify(1, null, Purchase.PurchaseType.CARD, 65.4d, null));

		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(1);
		Assertions.assertEquals(1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(65.4d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void  modifyPurchaseExistingArguments() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.of(2010, 3, 5), Purchase.PurchaseType.CARD, 10d, "abc");
		pl.add(LocalDate.of(2011, 4, 6), Purchase.PurchaseType.CASH, 20d, "xyz");

		Assertions.assertTrue(pl.modify(1, LocalDate.of(2012, 8, 4), Purchase.PurchaseType.INTERNET, 30d, "ooo"));

		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(1);
		Assertions.assertEquals(1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2012, 8, 4), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(30d, p.getPurchaseValue());
		Assertions.assertEquals("ooo", p.getPurchaseDescription());
	}

	@Test
	void deletePurchaseWrongId() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.of(2010, 3, 5), Purchase.PurchaseType.CARD, 10d, "abc");
		pl.add(LocalDate.of(2011, 4, 6), Purchase.PurchaseType.CASH, 20d, "xyz");

		Assertions.assertFalse(pl.delete(3));

		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(1);
		Assertions.assertEquals(1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20d, p.getPurchaseValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void deletePurchaseRightId() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.of(2010, 3, 5), Purchase.PurchaseType.CARD, 10d, "abc");
		pl.add(LocalDate.of(2011, 4, 6), Purchase.PurchaseType.CASH, 20d, "xyz");
		pl.add(LocalDate.of(2012, 8, 4), Purchase.PurchaseType.INTERNET, 30d, "ooo");

		Assertions.assertTrue(pl.delete(1));

		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(0);
		Assertions.assertEquals(0, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(2);
		Assertions.assertEquals(2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2012, 8, 4), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(30d, p.getPurchaseValue());
		Assertions.assertEquals("ooo", p.getPurchaseDescription());
	}

	@Test
	void clearList() {
		PurchaseList pl = new PurchaseList();
		pl.add(LocalDate.of(2010, 3, 5), Purchase.PurchaseType.CARD, 10d, "abc");
		pl.add(LocalDate.of(2011, 4, 6), Purchase.PurchaseType.CASH, 20d, "xyz");
		pl.add(LocalDate.of(2012, 8, 4), Purchase.PurchaseType.INTERNET, 30d, "ooo");
		pl.clear();
		Assertions.assertEquals(0, pl.count());
	}
}