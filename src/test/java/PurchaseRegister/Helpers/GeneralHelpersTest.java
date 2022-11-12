package PurchaseRegister.Helpers;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class GeneralHelpersTest {

	@Test
	void correctDateComponents() {
		Assertions.assertEquals(LocalDate.now(), GeneralHelpers.correctDateComponents(LocalDate.MAX.getYear() + 1, 1, 2));
		Assertions.assertEquals(LocalDate.now(), GeneralHelpers.correctDateComponents(2000, -1, 2));
		Assertions.assertEquals(LocalDate.now(), GeneralHelpers.correctDateComponents(2000, 1, -2));
		Assertions.assertEquals(LocalDate.now(), GeneralHelpers.correctDateComponents(2000, 2, 30));
		Assertions.assertEquals(LocalDate.of(2000, 1, 2), GeneralHelpers.correctDateComponents(2000, 1, 2));
	}

	@Test
	void multiLineToSingleLine() {
		Assertions.assertNull(GeneralHelpers.multiLineToSingleLine(null, " | "));
		Assertions.assertNull(GeneralHelpers.multiLineToSingleLine("abc\nxyz", null));
		Assertions.assertNull(GeneralHelpers.multiLineToSingleLine(null, null));
		Assertions.assertEquals("abc | xyz", GeneralHelpers.multiLineToSingleLine("abc\nxyz", " | "));
	}

	@Test
	void purchaseToLine() {
		Purchase purchase = new Purchase(LocalDate.of(964, 4, 6), Purchase.PurchaseType.CASH, 36d, "abc\nxyz");
		String line = GeneralHelpers.purchaseToLine(purchase, " | ");
		Assertions.assertTrue(line.equals("0964-04-06  CASH  36.00  abc | xyz") || line.equals("0964-04-06  CASH  36,00  abc | xyz"));
	}
}