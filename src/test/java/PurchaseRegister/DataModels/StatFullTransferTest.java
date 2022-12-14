package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.math.*;

class StatFullTransferTest {

	@Test
	void constructorAndGetters() {
		StatFullTransfer statFullTransfer = new StatFullTransfer(BigDecimal.valueOf(65.4), 3L, BigDecimal.valueOf(12.4));
		Assertions.assertEquals(65.4D, statFullTransfer.getTotal().doubleValue());
		Assertions.assertEquals(3L, statFullTransfer.getCount());
		Assertions.assertEquals(12.4D, statFullTransfer.getAverage().doubleValue());
	}

}