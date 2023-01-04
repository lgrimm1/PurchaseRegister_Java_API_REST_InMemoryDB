package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

class StatAnnualTransferTest {

	@Test
	void constructorAndGetters() {
		StatAnnualTransfer statAnnualTransfer = new StatAnnualTransfer(2010, 65.4D, 3, 12.4D);
		Assertions.assertEquals(2010, statAnnualTransfer.year());
		Assertions.assertEquals(65.4D, statAnnualTransfer.total());
		Assertions.assertEquals(3, statAnnualTransfer.count());
		Assertions.assertEquals(12.4D, statAnnualTransfer.average());
	}

}