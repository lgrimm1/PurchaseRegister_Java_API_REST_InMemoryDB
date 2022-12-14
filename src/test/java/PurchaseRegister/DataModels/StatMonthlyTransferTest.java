package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.math.*;

class StatMonthlyTransferTest {

	@Test
	void constructorAndGetters() {
		StatMonthlyTransfer statMonthlyTransfer = new StatMonthlyTransfer(2010L, 6L, BigDecimal.valueOf(65.4), 3L, BigDecimal.valueOf(12.4));
		Assertions.assertEquals(2010L, statMonthlyTransfer.getYear());
		Assertions.assertEquals(6L, statMonthlyTransfer.getMonth());
		Assertions.assertEquals(65.4D, statMonthlyTransfer.getTotal().doubleValue());
		Assertions.assertEquals(3L, statMonthlyTransfer.getCount());
		Assertions.assertEquals(12.4D, statMonthlyTransfer.getAverage().doubleValue());
	}

}