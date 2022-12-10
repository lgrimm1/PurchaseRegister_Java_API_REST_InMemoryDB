package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StatFulltotalTransferTest {

	@Test
	void constructorAndGetters() {
		StatFulltotalTransfer smft = new StatFulltotalTransfer(65.4d, 3, 12.4d);
		Assertions.assertEquals(65.4d, smft.getTotal());
		Assertions.assertEquals(3, smft.getCount());
		Assertions.assertEquals(12.4d, smft.getAverage());
	}

}