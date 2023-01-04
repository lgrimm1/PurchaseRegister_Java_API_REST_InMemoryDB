package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

class StatFullTransferTest {

	@Test
	void constructorAndGetters() {
		StatFullTransfer statFullTransfer = new StatFullTransfer(65.4D, 3, 12.4D);
		Assertions.assertEquals(65.4D, statFullTransfer.total());
		Assertions.assertEquals(3, statFullTransfer.count());
		Assertions.assertEquals(12.4D, statFullTransfer.average());
	}

}