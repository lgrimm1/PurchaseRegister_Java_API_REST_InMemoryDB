package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;

class StatMapTest {

	@Test
	void newInstance() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertEquals(StatMap.StatType.ANNUAL, sm.getStatType());
		Assertions.assertEquals(0, sm.count());

		sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertEquals(StatMap.StatType.MONTHLY, sm.getStatType());
		Assertions.assertEquals(0, sm.count());
	}

	@Test
	void addNullPurchase() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertFalse(sm.put(null));
		Assertions.assertEquals(0, sm.count());

		sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertFalse(sm.put(null));
		Assertions.assertEquals(0, sm.count());
	}

	@Test
	void addPurchaseToNewYear() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 6, 2), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertEquals(1, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());

		stat = sm.get(LocalDate.of(2001, 6, 2));
		Assertions.assertNull(stat);

		stat = sm.get(LocalDate.of(2000, 7, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());

		stat = sm.get(LocalDate.of(2000, 6, 3));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());
	}

	@Test
	void addPurchaseToNewMonth() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 6, 2), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertEquals(1, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());

		stat = sm.get(LocalDate.of(2001, 6, 2));
		Assertions.assertNull(stat);

		stat = sm.get(LocalDate.of(2000, 7, 2));
		Assertions.assertNull(stat);

		stat = sm.get(LocalDate.of(2000, 6, 3));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());
	}

	@Test
	void addPurchasesToSameYear() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 6, 2), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 8, 3), Purchase.PurchaseType.CARD, 36d, "something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2000, 12, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48d, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(24d, stat.getAverage());
	}

	@Test
	void addPurchasesToDifferentYears() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 6, 2), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2001, 6, 2), Purchase.PurchaseType.CARD, 36d, "something")));

		Assertions.assertEquals(2, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 12, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());

		stat = sm.get(LocalDate.of(2001, 12, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(36d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(36d, stat.getAverage());
	}

	@Test
	void addPurchasesToSameMonth() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 6, 2), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 6, 3), Purchase.PurchaseType.CARD, 36d, "something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2000, 6, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48d, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(24d, stat.getAverage());
	}

	@Test
	void addPurchasesToDifferentMonths() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 6, 2), Purchase.PurchaseType.CARD, 12d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2000, 8, 2), Purchase.PurchaseType.CARD, 36d, "something")));
		Assertions.assertTrue(sm.put(new Purchase(1, LocalDate.of(2001, 6, 2), Purchase.PurchaseType.CARD, 50d, "something")));

		Assertions.assertEquals(3, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());

		stat = sm.get(LocalDate.of(2000, 8, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(36d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(36d, stat.getAverage());

		stat = sm.get(LocalDate.of(2001, 6, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(50d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(50d, stat.getAverage());
	}
}