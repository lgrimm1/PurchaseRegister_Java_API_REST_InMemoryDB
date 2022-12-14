package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.math.*;

class StatAnnualTransferTest {

	@Test
	void constructorAndGetters() {
		StatAnnualTransfer statAnnualTransfer = new StatAnnualTransfer(2010L, BigDecimal.valueOf(65.4), 3L, BigDecimal.valueOf(12.4));
		Assertions.assertEquals(2010L, statAnnualTransfer.getYear());
		Assertions.assertEquals(65.4D, statAnnualTransfer.getTotal().doubleValue());
		Assertions.assertEquals(3L, statAnnualTransfer.getCount());
		Assertions.assertEquals(12.4D, statAnnualTransfer.getAverage().doubleValue());
	}

}