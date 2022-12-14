package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.math.*;
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
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertEquals(1, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());

		stat = sm.get(LocalDate.of(2001, 6, 2));
		Assertions.assertNull(stat);

		stat = sm.get(LocalDate.of(2000, 7, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());

		stat = sm.get(LocalDate.of(2000, 6, 3));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());
	}

	@Test
	void addAnnualPurchasesFromSameYear() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 8, 3),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(36),
				"something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2000, 12, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48D, stat.getTotal().doubleValue());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(24D, stat.getAverage().doubleValue());
	}

	@Test
	void addAnnualPurchasesFromDifferentYears() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2001, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(36),
				"something")));

		Assertions.assertEquals(2, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 12, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());

		stat = sm.get(LocalDate.of(2001, 12, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(36D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(36D, stat.getAverage().doubleValue());
	}

	@Test
	void addFirstMonthlyPurchase() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertEquals(1, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());

		stat = sm.get(LocalDate.of(2001, 6, 2));
		Assertions.assertNull(stat);

		stat = sm.get(LocalDate.of(2000, 7, 2));
		Assertions.assertNull(stat);

		stat = sm.get(LocalDate.of(2000, 6, 3));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());
	}

	@Test
	void addMonthlyPurchasesFromSameMonth() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 3),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(36),
				"something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2000, 6, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48D, stat.getTotal().doubleValue());
		Assertions.assertEquals(2L, stat.getCount());
		Assertions.assertEquals(24D, stat.getAverage().doubleValue());
	}

	@Test
	void addMonthlyPurchasesFromDifferentMonths() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 8, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(36),
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2001, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(50),
				"something")));

		Assertions.assertEquals(3, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());

		stat = sm.get(LocalDate.of(2000, 8, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(36D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(36D, stat.getAverage().doubleValue());

		stat = sm.get(LocalDate.of(2001, 6, 12));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(50D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(50D, stat.getAverage().doubleValue());
	}

	@Test
	void addFirstFullPurchase() {
		StatMap sm = new StatMap(StatMap.StatType.FULL);
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertEquals(1, sm.count());

		Stat stat = sm.get(LocalDate.of(2000, 6, 2));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());

		stat = sm.get(LocalDate.of(2001, 7, 8));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(12D, stat.getTotal().doubleValue());
		Assertions.assertEquals(1L, stat.getCount());
		Assertions.assertEquals(12D, stat.getAverage().doubleValue());
	}

	@Test
	void addFullPurchasesFromDifferentDates() {
		StatMap sm = new StatMap(StatMap.StatType.FULL);
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something")));
		Assertions.assertTrue(sm.put(new Purchase(
				1L,
				LocalDate.of(2001, 8, 3),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(36),
				"something")));

		Assertions.assertEquals(1, sm.count());
		Stat stat = sm.get(LocalDate.of(2010, 12, 1));
		Assertions.assertNotNull(stat);
		Assertions.assertEquals(48D, stat.getTotal().doubleValue());
		Assertions.assertEquals(2L, stat.getCount());
		Assertions.assertEquals(24D, stat.getAverage().doubleValue());
	}

	@Test
	void exportAnnualStream() {
		StatMap sm = new StatMap(StatMap.StatType.ANNUAL);
		sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something"));
		sm.put(new Purchase(
				2L,
				LocalDate.of(2000, 8, 12),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(24),
				"something"));
		sm.put(new Purchase(
				3L,
				LocalDate.of(2001, 12, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(48),
				"something"));

		Assertions.assertEquals(2, sm.stream().count());
		int yearAsSummarizedValue = sm.stream()
				.mapToInt(entry -> entry.getKey().getYear())
				.sum();
		Assertions.assertEquals(4001, yearAsSummarizedValue);
		double totalValue = sm.stream()
				.mapToDouble(entry -> entry.getValue().getTotal().doubleValue())
				.sum();
		Assertions.assertEquals(84D, totalValue);
	}

	@Test
	void exportMonthlyStream() {
		StatMap sm = new StatMap(StatMap.StatType.MONTHLY);
		sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something"));
		sm.put(new Purchase(
				2L,
				LocalDate.of(2000, 6, 12),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(24),
				"something"));
		sm.put(new Purchase(
				3L,
				LocalDate.of(2000, 12, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(48),
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
				.mapToDouble(entry -> entry.getValue().getTotal().doubleValue())
				.sum();
		Assertions.assertEquals(84D, totalValue);
	}

	@Test
	void exportFullStream() {
		StatMap sm = new StatMap(StatMap.StatType.FULL);
		sm.put(new Purchase(
				1L,
				LocalDate.of(2000, 6, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(12),
				"something"));
		sm.put(new Purchase(
				2L,
				LocalDate.of(2000, 6, 12),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(24),
				"something"));
		sm.put(new Purchase(
				3L,
				LocalDate.of(2000, 12, 2),
				Purchase.PurchaseType.CARD,
				BigDecimal.valueOf(48),
				"something"));

		Assertions.assertEquals(1, sm.stream().count());
		double totalValue = sm.stream()
				.mapToDouble(entry -> entry.getValue().getTotal().doubleValue())
				.sum();
		Assertions.assertEquals(84d, totalValue);
	}
}