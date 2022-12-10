package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import static org.mockito.Mockito.when;

class PurchaseServiceTest {
	private PurchaseStorage purchaseStorage;
	private PurchaseService purchaseService;

	@BeforeEach
	void setUp() {
		purchaseStorage = Mockito.mock(PurchaseStorage.class);
		when(purchaseStorage.getMinimumId())
				.thenReturn(Long.MIN_VALUE);
		purchaseService = new PurchaseService(purchaseStorage);
	}

	@Test
	void getPurchaseByIdWithNoResult() {
		when(purchaseStorage.get(3))
				.thenReturn(null);

		Assertions.assertNull(purchaseService.getPurchaseById(3));
	}

	@Test
	void getPurchaseByIdWithResult() {
		when(purchaseStorage.get(purchaseStorage.getMinimumId() + 1))
				.thenReturn(new Purchase(
						Long.MIN_VALUE + 1,
						LocalDate.of(2011, 4, 6),
						Purchase.PurchaseType.CASH,
						20d,
						"xyz"));

		Purchase p = purchaseService.getPurchaseById(purchaseStorage.getMinimumId() + 1);
		Assertions.assertNotNull(p);
		Assertions.assertEquals(p, purchaseStorage.get(purchaseStorage.getMinimumId() + 1));
	}

	@Test
	void addNewPurchaseWithNoResult() {
		when(purchaseStorage.add(
				null,
				null,
				12d,
				null))
				.thenReturn(null);

		Assertions.assertNull(purchaseService.addNewPurchase(new Purchase(3,
				null,
				null,
				12d,
				null)));
	}

	@Test
	void addNewPurchaseWithResult() {
		long minimumId = purchaseStorage.getMinimumId();
		when(purchaseStorage.add(
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12d,
				"abc")).thenReturn(minimumId);

		Assertions.assertEquals(minimumId, purchaseService.addNewPurchase(new Purchase(
				3,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12d,
				"abc")));
	}

	@Test
	void modifyPurchaseWithNoResult() {
		when(purchaseStorage.modify(
				3,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12d,
				"abc"
				))
				.thenReturn(false);

		Assertions.assertFalse(purchaseService.modifyPurchase(new Purchase(3,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12d,
				"abc")));
	}

	@Test
	void modifyPurchaseWithResult() {
		when(purchaseStorage.modify(
				3,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12d,
				null
				))
				.thenReturn(true);

		Assertions.assertTrue(purchaseService.modifyPurchase(new Purchase(
				3,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12d,
				null)));
	}

	@Test
	void deletePurchaseWithNoResult() {
		when(purchaseStorage.delete(3))
				.thenReturn(false);

		Assertions.assertFalse(purchaseService.deletePurchase(3));
	}

	@Test
	void deletePurchaseWithResult() {
		when(purchaseStorage.delete(3))
				.thenReturn(true);

		Assertions.assertTrue(purchaseService.deletePurchase(3));
	}

	@Test
	void getPurchases() {
		List<Purchase> purchaseList = List.of(
				new Purchase(1, LocalDate.of(2010, 6, 3), Purchase.PurchaseType.CARD, 12d, "abc")
		);
		when(purchaseStorage.stream())
				.thenReturn(purchaseList.stream());

		Assertions.assertEquals(purchaseList, purchaseService.getPurchases());
	}

	@Test
	void deletePurchases() {
		List<Long> ids = List.of(1L, 5L, 400L);
		when(purchaseStorage.delete(1L))
				.thenReturn(true);
		when(purchaseStorage.delete(5L))
				.thenReturn(true);
		when(purchaseStorage.delete(400L))
				.thenReturn(false);

		Assertions.assertEquals(List.of(1L, 5L), purchaseService.deletePurchases(ids));
	}

	@Test
	void countPurchases() {
		when(purchaseStorage.count())
				.thenReturn(12);

		Assertions.assertEquals(12, purchaseService.countPurchases());
	}

	@Test
	void generateAnnualStat() {
		List<Purchase> purchaseList = List.of(
				new Purchase(1, LocalDate.of(2010, 6, 3), Purchase.PurchaseType.CARD, 12D, "abc"),
				new Purchase(1, LocalDate.of(2010, 8, 16), Purchase.PurchaseType.CARD, 24D, "abc"),
				new Purchase(1, LocalDate.of(2020, 6, 3), Purchase.PurchaseType.CARD, 48D, "abc")
		);
		when(purchaseStorage.stream())
				.thenReturn(purchaseList.stream());

		List<StatAnnualTransfer> generatedStatElements = purchaseService.generateAnnualStat();
/*
		expected result:
		(2010, 36D, 2, 18D),
		(2020, 48D, 1, 48D)
*/

		Assertions.assertEquals(2, generatedStatElements.size());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToInt(StatAnnualTransfer::getYear)
				.filter(n -> n == 2010)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToInt(StatAnnualTransfer::getYear)
				.filter(n -> n == 2020)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatAnnualTransfer::getTotal)
				.filter(n -> n == 36D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatAnnualTransfer::getTotal)
				.filter(n -> n == 48D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToInt(StatAnnualTransfer::getCount)
				.filter(n -> n == 1)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToInt(StatAnnualTransfer::getCount)
				.filter(n -> n == 2)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatAnnualTransfer::getAverage)
				.filter(n -> n == 18D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatAnnualTransfer::getAverage)
				.filter(n -> n == 48D)
				.count());
	}

	@Test
	void generateMonthlyStat() {
		List<Purchase> purchaseList = List.of(
				new Purchase(1, LocalDate.of(2010, 6, 3), Purchase.PurchaseType.CARD, 12D, "abc"),
				new Purchase(1, LocalDate.of(2010, 6, 16), Purchase.PurchaseType.CARD, 24D, "abc"),
				new Purchase(1, LocalDate.of(2010, 8, 30), Purchase.PurchaseType.CARD, 48D, "abc"),
				new Purchase(1, LocalDate.of(2020, 6, 16), Purchase.PurchaseType.CARD, 99D, "abc")
		);
		when(purchaseStorage.stream())
				.thenReturn(purchaseList.stream());

		List<StatMonthlyTransfer> generatedStatElements = purchaseService.generateMonthlyStat();
/*
		expected result:
		(2010, 6, 36D, 2, 18D),
		(2010, 8, 48D, 1, 48D),
		(2020, 6, 99D, 1, 99D)
*/

		Assertions.assertEquals(3, generatedStatElements.size());

		Assertions.assertEquals(2, generatedStatElements.stream()
				.mapToInt(StatMonthlyTransfer::getYear)
				.filter(n -> n == 2010)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToInt(StatMonthlyTransfer::getYear)
				.filter(n -> n == 2020)
				.count());

		Assertions.assertEquals(2, generatedStatElements.stream()
				.mapToInt(StatMonthlyTransfer::getMonth)
				.filter(n -> n == 6)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToInt(StatMonthlyTransfer::getMonth)
				.filter(n -> n == 8)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatMonthlyTransfer::getTotal)
				.filter(n -> n == 36D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatMonthlyTransfer::getTotal)
				.filter(n -> n == 48D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatMonthlyTransfer::getTotal)
				.filter(n -> n == 99D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToInt(StatMonthlyTransfer::getCount)
				.filter(n -> n == 2)
				.count());

		Assertions.assertEquals(2, generatedStatElements.stream()
				.mapToInt(StatMonthlyTransfer::getCount)
				.filter(n -> n == 1)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatMonthlyTransfer::getAverage)
				.filter(n -> n == 18D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatMonthlyTransfer::getAverage)
				.filter(n -> n == 48D)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToDouble(StatMonthlyTransfer::getAverage)
				.filter(n -> n == 99D)
				.count());
	}

	@Test
	void generateFulltotalStat() {
		List<Purchase> purchaseList = List.of(
				new Purchase(1, LocalDate.of(2010, 6, 3), Purchase.PurchaseType.CARD, 12D, "abc"),
				new Purchase(1, LocalDate.of(2010, 8, 16), Purchase.PurchaseType.CARD, 24D, "abc"),
				new Purchase(1, LocalDate.of(2020, 6, 3), Purchase.PurchaseType.CARD, 48D, "abc")
		);
		when(purchaseStorage.stream())
				.thenReturn(purchaseList.stream());

		StatFulltotalTransfer generatedStatElements = purchaseService.generateFulltotalStat();
/*
		expected result:
		(84D, 3, 28D)
*/

		Assertions.assertEquals(84D, generatedStatElements.getTotal());
		Assertions.assertEquals(3, generatedStatElements.getCount());
		Assertions.assertEquals(28D, generatedStatElements.getAverage());
	}
}