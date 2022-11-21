package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;

class PurchaseTest {

	@Test
	void dateIsNullTypeIsNullDescriptionIsNull() {
		Purchase p = new Purchase(3, null, null, 12d, null);
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertNull(p.getPurchaseDate());
		Assertions.assertNull(p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertNull(p.getPurchaseDescription());
	}

	@Test
	void dateIsNull() {
		Purchase p = new Purchase(3, null, Purchase.PurchaseType.CARD, 12d, "abc");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertNull(p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());
	}

	@Test
	void typeIsNull() {
		Purchase p = new Purchase(3, LocalDate.now(), null, 12d, "abc");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertNull(p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());
	}

	@Test
	void descriptionIsNull() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CARD, 12d, null);
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertNull(p.getPurchaseDescription());
	}

	@Test
	void idIsLongMinimum() {
		Purchase p = new Purchase(Long.MIN_VALUE, LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "");
		Assertions.assertEquals(Long.MIN_VALUE, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void idIsLongMaximum() {
		Purchase p = new Purchase(Long.MAX_VALUE, LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "");
		Assertions.assertEquals(Long.MAX_VALUE, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void typeIsCARD() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void typeIsCASH() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CASH, 12d, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void typeIsINTERNET() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.INTERNET, 12d, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.INTERNET, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void valueIsDoubleMinimum() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CARD, Double.MIN_VALUE, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(Double.MIN_VALUE, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void typeIsDoubleMaximum() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CARD, Double.MAX_VALUE, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(Double.MAX_VALUE, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void descriptionIsEmpty() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void descriptionHasValue() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "abc");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());
	}
}