package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import java.math.*;
import java.time.*;
import java.util.*;

class PurchaseStorageTest {

	@Test
	void createNewStorage() {
		PurchaseStorage pl = new PurchaseStorage();
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void getMinMaxPossibleIds() {
		PurchaseStorage pl = new PurchaseStorage();
		Assertions.assertEquals(Long.MIN_VALUE + 1, pl.getMinimumId());
		Assertions.assertEquals(Long.MAX_VALUE, pl.getMaximumId());
	}

	@Test
	void addPurchaseNullArguments() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				null,
				null,
				BigDecimal.valueOf(65.4),
				null);
		Assertions.assertEquals(Long.MIN_VALUE, id);
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void addPurchaseNullDate() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				null,
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(65.4),
				"abc");
		Assertions.assertEquals(Long.MIN_VALUE, id);
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void addPurchaseNullType() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				LocalDate.now(),
				null,
				BigDecimal.valueOf(65.4),
				"abc");
		Assertions.assertEquals(Long.MIN_VALUE, id);
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void addPurchaseNullValue() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				LocalDate.now(),
				Purchase.PurchaseType.INTERNET,
				null,
				"abc");
		Assertions.assertEquals(Long.MIN_VALUE, id);
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void addPurchaseNullDescription() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				LocalDate.now(),
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(65.4),
				null);
		Assertions.assertEquals(Long.MIN_VALUE, id);
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void addCardPurchase() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(65.4),
				"something");
		Assertions.assertEquals(pl.getMinimumId(), id);
		Assertions.assertEquals(1, pl.count());

		Purchase p = pl.get(id);
		Assertions.assertEquals(id, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(65.4D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("something", p.getPurchaseDescription());
	}

	@Test
	void addCashPurchase() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				LocalDate.now(),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(65.4),
				"something");
		Assertions.assertEquals(pl.getMinimumId(), id);
		Assertions.assertEquals(1, pl.count());

		Purchase p = pl.get(id);
		Assertions.assertEquals(id, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(65.4D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("something", p.getPurchaseDescription());
	}

	@Test
	void addInternetPurchase() {
		PurchaseStorage pl = new PurchaseStorage();
		long id = pl.add(
				LocalDate.now(),
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(65.4),
				"something");
		Assertions.assertEquals(pl.getMinimumId(), id);
		Assertions.assertEquals(1, pl.count());

		Purchase p = pl.get(id);
		Assertions.assertEquals(id, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(65.4D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("something", p.getPurchaseDescription());
	}

	@Test
	void addMoreElements() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");
		Assertions.assertEquals(pl.getMinimumId(), id1);
		Assertions.assertEquals(pl.getMinimumId() + 1, id2);
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void getPurchaseEmptyStorage() {
		PurchaseStorage pl = new PurchaseStorage();

		Assertions.assertNull(pl.get(pl.getMinimumId()));
	}

	@Test
	void getPurchaseWrongId() {
		PurchaseStorage pl = new PurchaseStorage();
		pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertNull(pl.get(3L));
	}

	@Test
	void getPurchaseRightId() {
		PurchaseStorage pl = new PurchaseStorage();
		pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Purchase p = pl.get(id2);
		Assertions.assertNotNull(p);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void modifyPurchaseWrongId() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertFalse(pl.modify(
				3L,
				null,
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(65.4),
				null));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void modifyPurchaseNullArguments() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertFalse(pl.modify(
				null,
				null,
				null,
				BigDecimal.valueOf(65.4),
				null));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void modifyPurchaseNullId() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertFalse(pl.modify(
				null,
				LocalDate.of(2012, 8, 4),
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(30),
				"ooo"));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void modifyPurchaseNullDate() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertFalse(pl.modify(
				id2,
				null,
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(65.4),
				"ooo"));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void modifyPurchaseNullType() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertFalse(pl.modify(
				id2,
				LocalDate.now(),
				null,
				BigDecimal.valueOf(65.4),
				"ooo"));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10d, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20d, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void modifyPurchaseNullDescription() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertFalse(pl.modify(
				id2,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(65.4),
				null));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void modifyPurchaseRightArguments() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertTrue(pl.modify(
				id2,
				LocalDate.of(2012, 8, 4),
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(30),
				"ooo"));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2012, 8, 4), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(30D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("ooo", p.getPurchaseDescription());
	}

	@Test
	void deletePurchaseWrongId() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");

		Assertions.assertFalse(pl.delete(3L));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id2);
		Assertions.assertEquals(id2, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2011, 4, 6), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(20D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("xyz", p.getPurchaseDescription());
	}

	@Test
	void deletePurchaseRightId() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");
		long id3 = pl.add(
				LocalDate.of(2012, 8, 4),
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(30),
				"ooo");

		Assertions.assertTrue(pl.delete(id2));
		Assertions.assertEquals(2, pl.count());

		Purchase p = pl.get(id1);
		Assertions.assertEquals(id1, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2010, 3, 5), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(10D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());

		p = pl.get(id3);
		Assertions.assertEquals(id3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.of(2012, 8, 4), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(30D, p.getPurchaseValue().doubleValue());
		Assertions.assertEquals("ooo", p.getPurchaseDescription());
	}

	@Test
	void clearList() {
		PurchaseStorage pl = new PurchaseStorage();
		pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");
		pl.add(
				LocalDate.of(2012, 8, 4),
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(30),
				"ooo");

		pl.clear();
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void stream() {
		PurchaseStorage pl = new PurchaseStorage();
		long id1 = pl.add(
				LocalDate.of(2010, 3, 5),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(10),
				"abc");
		long id2 = pl.add(
				LocalDate.of(2011, 4, 6),
				Purchase.PurchaseType.CASH,
				BigDecimal.valueOf(20),
				"xyz");
		long id3 = pl.add(
				LocalDate.of(2012, 8, 4),
				Purchase.PurchaseType.INTERNET,
				BigDecimal.valueOf(30),
				"ooo");

		List<Purchase> plist = pl.stream().toList();
		Assertions.assertEquals(3, plist.size());
		Assertions.assertEquals(pl.get(id1), plist.get(0));
		Assertions.assertEquals(pl.get(id2), plist.get(1));
		Assertions.assertEquals(pl.get(id3), plist.get(2));
	}
}