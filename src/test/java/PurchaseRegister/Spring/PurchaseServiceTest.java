package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.time.*;
import java.util.*;

import static org.mockito.Mockito.when;

class PurchaseServiceTest {
	private PurchaseStorage purchaseStorage;
	private PurchaseService purchaseService;

	@BeforeEach
	void setUp() {
		purchaseStorage = Mockito.mock(PurchaseStorage.class);
		when(purchaseStorage.getMinimumId())
				.thenReturn(Long.MIN_VALUE + 1);
		purchaseService = new PurchaseService(purchaseStorage);
	}

	@Test
	void getPurchaseByIdWithNoResult() {
		when(purchaseStorage.get(3L))
				.thenReturn(null);

		Assertions.assertNull(purchaseService.getPurchaseById(3L));
	}

	@Test
	void getPurchaseByIdWithResult() {
		when(purchaseStorage.get(purchaseStorage.getMinimumId()))
				.thenReturn(new Purchase(
						Long.MIN_VALUE + 1,
						LocalDate.of(2011, 4, 6),
						Purchase.PurchaseType.CASH,
						12D,
						"xyz"));

		Purchase p = purchaseService.getPurchaseById(purchaseStorage.getMinimumId());
		Assertions.assertNotNull(p);
		Assertions.assertEquals(p, purchaseStorage.get(purchaseStorage.getMinimumId()));
	}

	@Test
	void addNewPurchaseWithNoResult() {
		when(purchaseStorage.add(
				null,
				null,
				12D,
				null))
				.thenReturn(Long.MIN_VALUE);

		Assertions.assertEquals(Long.MIN_VALUE, purchaseService.addNewPurchase(new Purchase(
				3L,
				null,
				null,
				12D,
				null)));
	}

	@Test
	void addNewPurchaseWithResult() {
		when(purchaseStorage.add(
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12D,
				"abc"))
				.thenReturn(Long.MIN_VALUE + 1);

		Assertions.assertEquals(purchaseStorage.getMinimumId(), purchaseService.addNewPurchase(new Purchase(
				3L,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12D,
				"abc")));
	}

	@Test
	void modifyPurchaseWithNoResult() {
		when(purchaseStorage.modify(
				3L,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12D,
				"abc"
				))
				.thenReturn(false);

		Assertions.assertFalse(purchaseService.modifyPurchase(new Purchase(
				3L,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12D,
				"abc")));
	}

	@Test
	void modifyPurchaseWithResult() {
		when(purchaseStorage.modify(
				3L,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12D,
				null
				))
				.thenReturn(true);

		Assertions.assertTrue(purchaseService.modifyPurchase(new Purchase(
				3L,
				LocalDate.now(),
				Purchase.PurchaseType.CARD,
				12D,
				null)));
	}

	@Test
	void deletePurchaseWithNoResult() {
		when(purchaseStorage.delete(3))
				.thenReturn(false);

		Assertions.assertFalse(purchaseService.deletePurchase(3L));
	}

	@Test
	void deletePurchaseWithResult() {
		when(purchaseStorage.delete(3))
				.thenReturn(true);

		Assertions.assertTrue(purchaseService.deletePurchase(3L));
	}

	@Test
	void getPurchases() {
		List<Purchase> purchaseList = List.of(
				new Purchase(
						1L,
						LocalDate.of(2010, 6, 3),
						Purchase.PurchaseType.CARD,
						12D,
						"abc")
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
				new Purchase(
						1L,
						LocalDate.of(2010, 6, 3),
						Purchase.PurchaseType.CARD,
						12D,
						"abc"),
				new Purchase(
						1L,
						LocalDate.of(2010, 8, 16),
						Purchase.PurchaseType.CARD,
						24D,
						"abc"),
				new Purchase(
						1L,
						LocalDate.of(2020, 6, 3),
						Purchase.PurchaseType.CARD,
						48D,
						"abc")
		);
		when(purchaseStorage.stream())
				.thenReturn(purchaseList.stream());

		List<StatAnnualTransfer> generatedStatElements = purchaseService.generateAnnualStat();

/*
		expected result:
		(2010L, 36D, 2L, 18D),
		(2020L, 48D, 1L, 48D)
*/

		Assertions.assertEquals(2, generatedStatElements.size());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToLong(StatAnnualTransfer::getYear)
				.filter(n -> n == 2010)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToLong(StatAnnualTransfer::getYear)
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
				.mapToLong(StatAnnualTransfer::getCount)
				.filter(n -> n == 1)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToLong(StatAnnualTransfer::getCount)
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
				new Purchase(
						1L,
						LocalDate.of(2010, 6, 3),
						Purchase.PurchaseType.CARD,
						12D,
						"abc"),
				new Purchase(
						1L,
						LocalDate.of(2010, 6, 16),
						Purchase.PurchaseType.CARD,
						24D,
						"abc"),
				new Purchase(
						1L,
						LocalDate.of(2010, 8, 30),
						Purchase.PurchaseType.CARD,
						48D,
						"abc"),
				new Purchase(

						1L,
						LocalDate.of(2020, 6, 16),
						Purchase.PurchaseType.CARD,
						99D,
						"abc")
		);
		when(purchaseStorage.stream())
				.thenReturn(purchaseList.stream());

		List<StatMonthlyTransfer> generatedStatElements = purchaseService.generateMonthlyStat();

/*
		expected result:
		(2010L, 6L, 36D, 2L, 18D),
		(2010L, 8L, 48D, 1L, 48D),
		(2020L, 6L, 99D, 1L, 99D)
*/

		Assertions.assertEquals(3, generatedStatElements.size());

		Assertions.assertEquals(2, generatedStatElements.stream()
				.mapToLong(StatMonthlyTransfer::getYear)
				.filter(n -> n == 2010L)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToLong(StatMonthlyTransfer::getYear)
				.filter(n -> n == 2020L)
				.count());

		Assertions.assertEquals(2, generatedStatElements.stream()
				.mapToLong(StatMonthlyTransfer::getMonth)
				.filter(n -> n == 6L)
				.count());

		Assertions.assertEquals(1, generatedStatElements.stream()
				.mapToLong(StatMonthlyTransfer::getMonth)
				.filter(n -> n == 8L)
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
				.mapToLong(StatMonthlyTransfer::getCount)
				.filter(n -> n == 2L)
				.count());

		Assertions.assertEquals(2, generatedStatElements.stream()
				.mapToLong(StatMonthlyTransfer::getCount)
				.filter(n -> n == 1L)
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
	void generateFullStat() {
		List<Purchase> purchaseList = List.of(
				new Purchase(
						1L,
						LocalDate.of(2010, 6, 3),
						Purchase.PurchaseType.CARD,
						12D,
						"abc"),
				new Purchase(
						1L,
						LocalDate.of(2010, 8, 16),
						Purchase.PurchaseType.CARD,
						24D,
						"abc"),
				new Purchase(
						1L,
						LocalDate.of(2020, 6, 3),
						Purchase.PurchaseType.CARD,
						48D,
						"abc")
		);
		when(purchaseStorage.stream())
				.thenReturn(purchaseList.stream());

		StatFullTransfer generatedStatElements = purchaseService.generateFullStat();

/*
		expected result:
		(84D, 3L, 28D)
*/

		Assertions.assertEquals(84D, generatedStatElements.getTotal());
		Assertions.assertEquals(3L, generatedStatElements.getCount());
		Assertions.assertEquals(28D, generatedStatElements.getAverage());
	}
}
