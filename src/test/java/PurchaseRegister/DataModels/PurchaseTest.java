package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;

class PurchaseTest {

	@Test
	void dateIsNullDescriptionIsNullTypeIsCARDValueIs12() {
		Purchase p = new Purchase(null, Purchase.PurchaseType.CARD, 12d, null);
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void dateIsNullDescriptionIsEmpty() {
		Purchase p = new Purchase(null, Purchase.PurchaseType.CARD, 12d, "");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void descriptionIsEmpty() {
		Purchase p = new Purchase(LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void descriptionIsNotEmpty() {
		Purchase p = new Purchase(LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "abc");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void typeIsCASH() {
		Purchase p = new Purchase(LocalDate.now(), Purchase.PurchaseType.CASH, 12d, "");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
		Assertions.assertEquals("CASH", p.getPurchaseTypeString());
	}

	@Test
	void typeIsINTERNET() {
		Purchase p = new Purchase(LocalDate.now(), Purchase.PurchaseType.INTERNET, 12d, "");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
		Assertions.assertEquals("INET", p.getPurchaseTypeString());
	}

	@Test
	void valueIsNegative() {
		Purchase p = new Purchase(LocalDate.now(), Purchase.PurchaseType.CASH, -12d, "");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(-12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
		Assertions.assertEquals("CASH", p.getPurchaseTypeString());
	}

	@Test
	void wrongYear() {
		Purchase p = new Purchase(LocalDate.MAX.getYear() + 1, 1, 2, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("ABC", p.getPurchaseDescription());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void wrongMonth() {
		Purchase p = new Purchase(2000, -1, 2, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("ABC", p.getPurchaseDescription());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void wrongDay() {
		Purchase p = new Purchase(2000, 1, -2, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("ABC", p.getPurchaseDescription());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void invalidDate() {
		Purchase p = new Purchase(2000, 2, 30, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("ABC", p.getPurchaseDescription());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void validDate() {
		Purchase p = new Purchase(2000, 3, 10, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(LocalDate.of(2000, 3, 10), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("ABC", p.getPurchaseDescription());
		Assertions.assertEquals("CARD", p.getPurchaseTypeString());
	}

	@Test
	void comparingByMonth() {
		Purchase p1 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p2 = new Purchase(2000, 2, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p3 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(-1, p1.compareTo(p2));
		Assertions.assertEquals(1, p2.compareTo(p1));
		Assertions.assertEquals(0, p1.compareTo(p3));
		Assertions.assertEquals(0, p3.compareTo(p1));
	}

	@Test
	void comparingByYear() {
		Purchase p1 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p2 = new Purchase(2001, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p3 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(-1, p1.compareTo(p2));
		Assertions.assertEquals(1, p2.compareTo(p1));
		Assertions.assertEquals(0, p1.compareTo(p3));
		Assertions.assertEquals(0, p3.compareTo(p1));
	}

	@Test
	void comparingByDay() {
		Purchase p1 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p2 = new Purchase(2000, 1, 2, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p3 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(-1, p1.compareTo(p2));
		Assertions.assertEquals(1, p2.compareTo(p1));
		Assertions.assertEquals(0, p1.compareTo(p3));
		Assertions.assertEquals(0, p3.compareTo(p1));
	}

	@Test
	void comparingByType() {
		Purchase p1 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p2 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CASH, 12d, "ABC");
		Purchase p3 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(-1, p1.compareTo(p2));
		Assertions.assertEquals(1, p2.compareTo(p1));
		Assertions.assertEquals(0, p1.compareTo(p3));
		Assertions.assertEquals(0, p3.compareTo(p1));
	}

	@Test
	void comparingByValue() {
		Purchase p1 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p2 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 13d, "ABC");
		Purchase p3 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(-1, p1.compareTo(p2));
		Assertions.assertEquals(1, p2.compareTo(p1));
		Assertions.assertEquals(0, p1.compareTo(p3));
		Assertions.assertEquals(0, p3.compareTo(p1));
	}

	@Test
	void comparingByDescription() {
		Purchase p1 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Purchase p2 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "BBC");
		Purchase p3 = new Purchase(2000, 1, 1, Purchase.PurchaseType.CARD, 12d, "ABC");
		Assertions.assertEquals(-1, p1.compareTo(p2));
		Assertions.assertEquals(1, p2.compareTo(p1));
		Assertions.assertEquals(0, p1.compareTo(p3));
		Assertions.assertEquals(0, p3.compareTo(p1));
	}
}