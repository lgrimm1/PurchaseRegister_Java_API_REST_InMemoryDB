package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

class StatMonthlyTransferTest {

	@Test
	void constructorAndGetters() {
		StatMonthlyTransfer smat = new StatMonthlyTransfer(2010, 6, 65.4d, 3, 12.4d);
		Assertions.assertEquals(2010, smat.getYear());
		Assertions.assertEquals(6, smat.getMonth());
		Assertions.assertEquals(65.4d, smat.getTotal());
		Assertions.assertEquals(3, smat.getCount());
		Assertions.assertEquals(12.4d, smat.getAverage());
	}

}