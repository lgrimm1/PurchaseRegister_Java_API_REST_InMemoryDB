package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

class StatMapTest {

	@Test
	void newInstance() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertEquals(StatMap.StatType.ANNUAL, sm.getStatType());
		Assertions.assertEquals(0, sm.count());

		sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertEquals(StatMap.StatType.MONTHLY, sm.getStatType());
		Assertions.assertEquals(0, sm.count());

		sm = new StatMap(StatMap.StatType.FULL);
		Assertions.assertEquals(StatMap.StatType.FULL, sm.getStatType());
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

		sm = new StatMap(StatMap.StatType.FULL);
		Assertions.assertFalse(sm.put(null));
		Assertions.assertEquals(0, sm.count());
	}

	@Test
	void addFirstAnnualPurchase() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
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
	void addAnnualPurchasesFromSameYear() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 8, 3),
				Purchase.PurchaseType.CARD,
				36d,
				"something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2000, 12, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48d, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(24d, stat.getAverage());
	}

	@Test
	void addAnnualPurchasesFromDifferentYears() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2001, 6, 2),
				Purchase.PurchaseType.CARD,
				36d,
				"something")));

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
	void addFirstMonthlyPurchase() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
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
	void addMonthlyPurchasesFromSameMonth() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 3),
				Purchase.PurchaseType.CARD,
				36d,
				"something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2000, 6, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48d, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(24d, stat.getAverage());
	}

	@Test
	void addMonthlyPurchasesFromDifferentMonths() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 8, 2),
				Purchase.PurchaseType.CARD,
				36d,
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2001, 6, 2),
				Purchase.PurchaseType.CARD,
				50d,
				"something")));

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

	@Test
	void addFirstFulltotalPurchase() {
		StatMap sm = new StatMap(StatMap.StatType.FULL);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
		Assertions.assertEquals(1, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());

		stat = sm.get(LocalDate.of(2001, 7, 8));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(12d, stat.getAverage());
	}

	@Test
	void addFulltotalPurchasesFromDifferentDates() {
		StatMap sm = new StatMap(StatMap.StatType.FULL);
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1,
				LocalDate.of(2001, 8, 3),
				Purchase.PurchaseType.CARD,
				36d,
				"something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2010, 12, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48d, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(24d, stat.getAverage());
	}

	@Test
	void exportAnnualStream() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something"));
		sm.put(new Purchase(
				2,
				LocalDate.of(2000, 8, 12),
				Purchase.PurchaseType.CARD,
				24d,
				"something"));
		sm.put(new Purchase(
				3,
				LocalDate.of(2001, 12, 2),
				Purchase.PurchaseType.CARD,
				48d,
				"something"));

		Assertions.assertEquals(2, sm.stream().count());
		int yearAsSummarizedValue = sm.stream()
				.mapToInt(entry -> entry.getKey().getYear())
				.sum();
		Assertions.assertEquals(4001, yearAsSummarizedValue);
		double totalValue = sm.stream()
				.mapToDouble(entry -> entry.getValue().getTotal())
				.sum();
		Assertions.assertEquals(84d, totalValue);
	}

	@Test
	void exportMonthlyStream() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something"));
		sm.put(new Purchase(
				2,
				LocalDate.of(2000, 6, 12),
				Purchase.PurchaseType.CARD,
				24d,
				"something"));
		sm.put(new Purchase(
				3,
				LocalDate.of(2000, 12, 2),
				Purchase.PurchaseType.CARD,
				48d,
				"something"));

		Assertions.assertEquals(2, sm.stream().count());
		int yearAsSummarizedValue = sm.stream()
				.mapToInt(entry -> entry.getKey().getYear())
				.sum();
		Assertions.assertEquals(4000, yearAsSummarizedValue);
		int monthAsSummarizedValue = sm.stream()
				.mapToInt(entry -> entry.getKey().getMonthValue())
				.sum();
		Assertions.assertEquals(18, monthAsSummarizedValue);
		double totalValue = sm.stream()
				.mapToDouble(entry -> entry.getValue().getTotal())
				.sum();
		Assertions.assertEquals(84d, totalValue);
	}

	@Test
	void exportFulltotalStream() {
		StatMap sm = new StatMap(StatMap.StatType.FULL);
		sm.put(new Purchase(
				1,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				12d,
				"something"));
		sm.put(new Purchase(
				2,
				LocalDate.of(2000, 6, 12),
				Purchase.PurchaseType.CARD,
				24d,
				"something"));
		sm.put(new Purchase(
				3,
				LocalDate.of(2000, 12, 2),
				Purchase.PurchaseType.CARD,
				48d,
				"something"));

		Assertions.assertEquals(1, sm.stream().count());
		double totalValue = sm.stream()
				.mapToDouble(entry -> entry.getValue().getTotal())
				.sum();
		Assertions.assertEquals(84d, totalValue);
	}
}