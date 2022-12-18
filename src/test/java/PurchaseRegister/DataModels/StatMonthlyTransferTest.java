package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

class StatMonthlyTransferTest {

	@Test
	void constructorAndGetters() {
		StatMonthlyTransfer statMonthlyTransfer = new StatMonthlyTransfer(2010, 6, 65.4D, 3, 12.4D);
		Assertions.assertEquals(2010, statMonthlyTransfer.getYear());
		Assertions.assertEquals(6, statMonthlyTransfer.getMonth());
		Assertions.assertEquals(65.4D, statMonthlyTransfer.getTotal());
		Assertions.assertEquals(3, statMonthlyTransfer.getCount());
		Assertions.assertEquals(12.4D, statMonthlyTransfer.getAverage());
	}

}