package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

class StatAnnualTransferTest {

	@Test
	void constructorAndGetters() {
		StatAnnualTransfer statAnnualTransfer = new StatAnnualTransfer(2010, 65.4D, 3, 12.4D);
		Assertions.assertEquals(2010, statAnnualTransfer.getYear());
		Assertions.assertEquals(65.4D, statAnnualTransfer.getTotal());
		Assertions.assertEquals(3, statAnnualTransfer.getCount());
		Assertions.assertEquals(12.4D, statAnnualTransfer.getAverage());
	}

}