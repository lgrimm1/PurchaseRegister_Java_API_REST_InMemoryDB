package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;

class PurchaseTest {

	@Test
	void dateIsNullDescriptionIsNullTypeIsCARDValueIs12() {
		Purchase p = new Purchase(3, null, Purchase.PurchaseType.CARD, 12d, null);
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}

	@Test
	void dateIsNullDescriptionIsEmpty() {
		Purchase p = new Purchase(3, null, Purchase.PurchaseType.CARD, 12d, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
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
	void descriptionIsNotEmpty() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "abc");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(12d, p.getPurchaseValue());
		Assertions.assertEquals("abc", p.getPurchaseDescription());
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
	void valueIsNegative() {
		Purchase p = new Purchase(3, LocalDate.now(), Purchase.PurchaseType.CASH, -12d, "");
		Assertions.assertEquals(3, p.getPurchaseId());
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, p.getPurchaseType());
		Assertions.assertEquals(-12d, p.getPurchaseValue());
		Assertions.assertTrue(p.getPurchaseDescription().isEmpty());
	}
}