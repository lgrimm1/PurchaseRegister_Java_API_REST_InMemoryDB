package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.time.*;
import java.util.*;

class MainCubeTest {

	@Test
	void nullArguments() {
		MainCube mainCube = new MainCube(null, null, null);
		Assertions.assertTrue(mainCube.getMainCubePurchaseLines().isEmpty());
		Assertions.assertTrue(mainCube.getMainCubeSelectionIndexes().isEmpty());
		Assertions.assertTrue(mainCube.getMessage().isEmpty());
	}

	@Test
	void nullPurchaseList() {
		MainCube mainCube = new MainCube(null, new ArrayList<>(), "");
		Assertions.assertTrue(mainCube.getMainCubePurchaseLines().isEmpty());
		Assertions.assertTrue(mainCube.getMainCubeSelectionIndexes().isEmpty());
		Assertions.assertTrue(mainCube.getMessage().isEmpty());
	}

	@Test
	void nullIndexList() {
		MainCube mainCube = new MainCube(new PurchaseList(), null, "");
		Assertions.assertTrue(mainCube.getMainCubePurchaseLines().isEmpty());
		Assertions.assertTrue(mainCube.getMainCubeSelectionIndexes().isEmpty());
		Assertions.assertTrue(mainCube.getMessage().isEmpty());
	}

	@Test
	void nullMessage() {
		MainCube mainCube = new MainCube(new PurchaseList(), new ArrayList<>(), null);
		Assertions.assertTrue(mainCube.getMainCubePurchaseLines().isEmpty());
		Assertions.assertTrue(mainCube.getMainCubeSelectionIndexes().isEmpty());
		Assertions.assertTrue(mainCube.getMessage().isEmpty());
	}

	@Test
	void onePurchase() {
		PurchaseList purchaseList = new PurchaseList();
		purchaseList.add(new Purchase(LocalDate.of(10, 2, 3), Purchase.PurchaseType.CASH, 12d, "xyz"));
		MainCube mainCube = new MainCube(purchaseList, null, null);
		Assertions.assertEquals(1, mainCube.getMainCubePurchaseLines().size());
		Assertions.assertTrue(mainCube.getMainCubePurchaseLines().get(0).equals("0010-02-03  CASH  12.00  xyz") || mainCube.getMainCubePurchaseLines().get(0).equals("0010-02-03  CASH  12,00  xyz"));
	}

	@Test
	void morePurchases() {
		PurchaseList purchaseList = new PurchaseList();
		purchaseList.add(new Purchase(LocalDate.of(10, 2, 3), Purchase.PurchaseType.CASH, 12d, "xyz\n123"));
		purchaseList.add(new Purchase(LocalDate.of(2022, 10, 20), Purchase.PurchaseType.INTERNET, 24.3d, "qwe\newq\nwindow"));
		MainCube mainCube = new MainCube(purchaseList, null, null);
		Assertions.assertEquals(2, mainCube.getMainCubePurchaseLines().size());
		Assertions.assertTrue(mainCube.getMainCubePurchaseLines().get(0).equals("0010-02-03  CASH  12.00  xyz | 123") || mainCube.getMainCubePurchaseLines().get(0).equals("0010-02-03  CASH  12,00  xyz | 123"));
		Assertions.assertTrue(mainCube.getMainCubePurchaseLines().get(1).equals("2022-10-20  INET  24.30  qwe | ewq | window") || mainCube.getMainCubePurchaseLines().get(1).equals("2022-10-20  INET  24,30  qwe | ewq | window"));
	}

	@Test
	void oneIndex() {
		ArrayList<Integer> indexes = new ArrayList<>();
		indexes.add(2);
		MainCube mainCube = new MainCube(null, indexes, null);
		Assertions.assertEquals(1, mainCube.getMainCubeSelectionIndexes().size());
		Assertions.assertEquals(2, mainCube.getMainCubeSelectionIndexes().get(0));
	}

	@Test
	void moreIndexes() {
		ArrayList<Integer> indexes = new ArrayList<>();
		indexes.add(3);
		indexes.add(4);
		MainCube mainCube = new MainCube(null, indexes, null);
		Assertions.assertEquals(2, mainCube.getMainCubeSelectionIndexes().size());
		Assertions.assertEquals(3, mainCube.getMainCubeSelectionIndexes().get(0));
		Assertions.assertEquals(4, mainCube.getMainCubeSelectionIndexes().get(1));
	}

	@Test
	void message() {
		MainCube mainCube = new MainCube(null, null, "");
		Assertions.assertTrue(mainCube.getMessage().isEmpty());
		mainCube = new MainCube(null, null, "xyz");
		Assertions.assertEquals("xyz", mainCube.getMessage());
	}
}