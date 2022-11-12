package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class StatMapTest {

	@Test
	void newInstance() {
		StatMap sm = new StatMap();
		Assertions.assertEquals(0, sm.count());
	}

	@Test
	void addNullPurchase() {
		StatMap sm = new StatMap();
		Assertions.assertFalse(sm.put(null));
		Assertions.assertEquals(0, sm.count());
	}

	@Test
	void addValidPurchase() {
		StatMap sm = new StatMap();
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.now(), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertEquals(1, sm.count());
		Assertions.assertEquals(12d, sm.get(LocalDate.now()).getTotal());
		Assertions.assertEquals(1, sm.get(LocalDate.now()).getCount());
		Assertions.assertEquals(12d, sm.get(LocalDate.now()).getAverage());
	}

	@Test
	void addValidDataDifferentDates() {
		StatMap sm = new StatMap();
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2000, 12, 1), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2001, 5, 11), Purchase.PurchaseType.CARD, 24d, "something2")));

		Assertions.assertEquals(2, sm.count());

		Assertions.assertEquals(12d, sm.get(LocalDate.of(2000, 12, 1)).getTotal());
		Assertions.assertEquals(1, sm.get(LocalDate.of(2000, 12, 1)).getCount());
		Assertions.assertEquals(12d, sm.get(LocalDate.of(2000, 12, 1)).getAverage());

		Assertions.assertEquals(24d, sm.get(LocalDate.of(2001, 5, 11)).getTotal());
		Assertions.assertEquals(1, sm.get(LocalDate.of(2001, 5, 11)).getCount());
		Assertions.assertEquals(24d, sm.get(LocalDate.of(2001, 5, 11)).getAverage());
	}

	@Test
	void addValidDataSameDate() {
		StatMap sm = new StatMap();
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2000, 12, 1), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2000, 12, 1), Purchase.PurchaseType.CARD, 24d, "something2")));

		Assertions.assertEquals(1, sm.count());
		Assertions.assertEquals(36d, sm.get(LocalDate.of(2000, 12, 1)).getTotal());
		Assertions.assertEquals(2, sm.get(LocalDate.of(2000, 12, 1)).getCount());
		Assertions.assertEquals(18d, sm.get(LocalDate.of(2000, 12, 1)).getAverage());
	}

	@Test
	void addMoreData() {
		StatMap sm = new StatMap();
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2000, 12, 1), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2000, 12, 1), Purchase.PurchaseType.CARD, 24d, "something2")));
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2001, 5, 11), Purchase.PurchaseType.CARD, 2d, "something2")));
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2001, 5, 11), Purchase.PurchaseType.CARD, 6d, "something2")));
		Assertions.assertTrue(sm.put(new Purchase(LocalDate.of(2001, 5, 11), Purchase.PurchaseType.CARD, 4d, "something2")));

		Assertions.assertEquals(2, sm.count());

		Assertions.assertEquals(36d, sm.get(LocalDate.of(2000, 12, 1)).getTotal());
		Assertions.assertEquals(2, sm.get(LocalDate.of(2000, 12, 1)).getCount());
		Assertions.assertEquals(18d, sm.get(LocalDate.of(2000, 12, 1)).getAverage());

		Assertions.assertEquals(12d, sm.get(LocalDate.of(2001, 5, 11)).getTotal());
		Assertions.assertEquals(3, sm.get(LocalDate.of(2001, 5, 11)).getCount());
		Assertions.assertEquals(4d, sm.get(LocalDate.of(2001, 5, 11)).getAverage());
	}
}