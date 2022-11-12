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
	void addNullElement() {
		PurchaseList pl = new PurchaseList();
		Assertions.assertFalse(pl.add(null));
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void addValidElement() {
		PurchaseList pl = new PurchaseList();
		Assertions.assertTrue(pl.add(new Purchase(LocalDate.now(), Purchase.PurchaseType.CARD, 65.4d, "something")));
		Assertions.assertEquals(1, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(LocalDate.now(), p.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, p.getPurchaseType());
		Assertions.assertEquals(65.4d, p.getPurchaseValue());
		Assertions.assertEquals("something", p.getPurchaseDescription());
	}

	@Test
	void addMoreElements() {
		PurchaseList pl = new PurchaseList();
		Assertions.assertTrue(pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st")));
		Assertions.assertTrue(pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd")));
		Assertions.assertTrue(pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd")));
		Assertions.assertEquals(3, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(LocalDate.of(2000, 1, 16), p.getPurchaseDate());
		p = pl.get(1);
		Assertions.assertEquals(LocalDate.of(1999, 1, 16), p.getPurchaseDate());
		p = pl.get(2);
		Assertions.assertEquals(LocalDate.of(2001, 1, 16), p.getPurchaseDate());
	}

	@Test
	void modifyWrongIndex() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		Purchase p = new Purchase(2005, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st");
		Assertions.assertFalse(pl.modify(-6, p));
		Assertions.assertFalse(pl.modify(3, p));
		Assertions.assertEquals(3, pl.count());
		p = pl.get(0);
		Assertions.assertEquals(LocalDate.of(2000, 1, 16), p.getPurchaseDate());
		p = pl.get(1);
		Assertions.assertEquals(LocalDate.of(1999, 1, 16), p.getPurchaseDate());
		p = pl.get(2);
		Assertions.assertEquals(LocalDate.of(2001, 1, 16), p.getPurchaseDate());
	}

	@Test
	void modifyNullNewElement() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		Assertions.assertFalse(pl.modify(2, null));
		Assertions.assertEquals(3, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(LocalDate.of(2000, 1, 16), p.getPurchaseDate());
		p = pl.get(1);
		Assertions.assertEquals(LocalDate.of(1999, 1, 16), p.getPurchaseDate());
		p = pl.get(2);
		Assertions.assertEquals(LocalDate.of(2001, 1, 16), p.getPurchaseDate());
	}

	@Test
	void modifyValidArguments() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		Purchase p = new Purchase(2005, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st");
		Assertions.assertTrue(pl.modify(1, p));
		Assertions.assertEquals(3, pl.count());
		p = pl.get(0);
		Assertions.assertEquals(LocalDate.of(2000, 1, 16), p.getPurchaseDate());
		p = pl.get(1);
		Assertions.assertEquals(LocalDate.of(2005, 1, 16), p.getPurchaseDate());
		p = pl.get(2);
		Assertions.assertEquals(LocalDate.of(2001, 1, 16), p.getPurchaseDate());
	}

	@Test
	void deleteWrongIndex() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		Assertions.assertFalse(pl.delete(-6));
		Assertions.assertFalse(pl.delete(3));
		Assertions.assertEquals(3, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(LocalDate.of(2000, 1, 16), p.getPurchaseDate());
		p = pl.get(1);
		Assertions.assertEquals(LocalDate.of(1999, 1, 16), p.getPurchaseDate());
		p = pl.get(2);
		Assertions.assertEquals(LocalDate.of(2001, 1, 16), p.getPurchaseDate());
	}

	@Test
	void deleteValidIndex() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		Assertions.assertTrue(pl.delete(1));
		Assertions.assertEquals(2, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(LocalDate.of(2000, 1, 16), p.getPurchaseDate());
		p = pl.get(1);
		Assertions.assertEquals(LocalDate.of(2001, 1, 16), p.getPurchaseDate());
	}

	@Test
	void clearList() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		pl.clear();
		Assertions.assertEquals(0, pl.count());
	}

	@Test
	void sortList() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 9, 3, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		pl.sort();
		Assertions.assertEquals(4, pl.count());
		Purchase p = pl.get(0);
		Assertions.assertEquals(LocalDate.of(1999, 1, 16), p.getPurchaseDate());
		p = pl.get(1);
		Assertions.assertEquals(LocalDate.of(2000, 1, 16), p.getPurchaseDate());
		p = pl.get(2);
		Assertions.assertEquals(LocalDate.of(2001, 1, 16), p.getPurchaseDate());
		p = pl.get(3);
		Assertions.assertEquals(LocalDate.of(2001, 9, 3), p.getPurchaseDate());
	}

	@Test
	void exportStream() {
		PurchaseList pl = new PurchaseList();
		pl.add(new Purchase(2000, 1, 16, Purchase.PurchaseType.CARD, 1d, "1st"));
		pl.add(new Purchase(1999, 1, 16, Purchase.PurchaseType.CASH, 2d, "2nd"));
		pl.add(new Purchase(2001, 9, 3, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		pl.add(new Purchase(2001, 1, 16, Purchase.PurchaseType.INTERNET, 3d, "3rd"));
		Assertions.assertEquals(4, pl.stream().map(Purchase::getPurchaseDate).count());
		Assertions.assertEquals(9d, pl.stream().map(Purchase::getPurchaseValue).mapToDouble(v -> v).sum());
	}
}