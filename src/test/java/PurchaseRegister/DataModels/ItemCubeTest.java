package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;

class ItemCubeTest {

	@Test
	void nullArgumentsNotNewItem() {
		ItemCube itemCube = new ItemCube(1, null, null);
		Assertions.assertEquals(1, itemCube.getItemIndex());
		Assertions.assertEquals(LocalDate.now(), itemCube.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, itemCube.getPurchaseType());
		Assertions.assertEquals(0d, itemCube.getPurchaseValue());
		Assertions.assertTrue(itemCube.getPurchaseDescription().isEmpty());
		Assertions.assertTrue(itemCube.getMessage().isEmpty());
	}

	@Test
	void nullArgumentsNewItem() {
		ItemCube itemCube = new ItemCube(1, null, null);
		Assertions.assertEquals(1, itemCube.getItemIndex());
		Assertions.assertEquals(LocalDate.now(), itemCube.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, itemCube.getPurchaseType());
		Assertions.assertEquals(0d, itemCube.getPurchaseValue());
		Assertions.assertTrue(itemCube.getPurchaseDescription().isEmpty());
		Assertions.assertTrue(itemCube.getMessage().isEmpty());
	}

	@Test
	void nullPurchase() {
		ItemCube itemCube = new ItemCube(1, null, "abc");
		Assertions.assertEquals(1, itemCube.getItemIndex());
		Assertions.assertEquals(LocalDate.now(), itemCube.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CARD, itemCube.getPurchaseType());
		Assertions.assertEquals(0d, itemCube.getPurchaseValue());
		Assertions.assertTrue(itemCube.getPurchaseDescription().isEmpty());
		Assertions.assertEquals("abc", itemCube.getMessage());
	}

	@Test
	void nullMessage() {
		ItemCube itemCube = new ItemCube(1, new Purchase(null, Purchase.PurchaseType.CASH, 12d, "xyz"), null);
		Assertions.assertEquals(1, itemCube.getItemIndex());
		Assertions.assertEquals(LocalDate.now(), itemCube.getPurchaseDate());
		Assertions.assertEquals(Purchase.PurchaseType.CASH, itemCube.getPurchaseType());
		Assertions.assertEquals(12d, itemCube.getPurchaseValue());
		Assertions.assertEquals("xyz", itemCube.getPurchaseDescription());
		Assertions.assertTrue(itemCube.getMessage().isEmpty());
	}

	@Test
	void itemIndex() {
		ItemCube itemCube = new ItemCube(-1, new Purchase(null, Purchase.PurchaseType.CASH, 12d, "xyz"), null);
		Assertions.assertEquals(-1, itemCube.getItemIndex());
		itemCube = new ItemCube(5, new Purchase(null, Purchase.PurchaseType.CASH, 12d, "xyz"), null);
		Assertions.assertEquals(5, itemCube.getItemIndex());
	}
}