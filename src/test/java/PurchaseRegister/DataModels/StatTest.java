package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import java.math.*;

class StatTest {

	@Test
	void constructStat() {
		Stat stat = new Stat(BigDecimal.valueOf(50), 2L);
		Assertions.assertEquals(50D, stat.getTotal().doubleValue());
		Assertions.assertEquals(2L, stat.getCount());
		Assertions.assertEquals(25D, stat.getAverage().doubleValue());

		stat = new Stat(BigDecimal.valueOf(-5), 5L);
		Assertions.assertEquals(-5D, stat.getTotal().doubleValue());
		Assertions.assertEquals(5L, stat.getCount());
		Assertions.assertEquals(-1D, stat.getAverage().doubleValue());
	}

	@Test
	void copy() {
		Stat stat = new Stat(BigDecimal.valueOf(50), 2L);
		Stat statCopy = stat.deepCopy();
		stat = new Stat(BigDecimal.valueOf(2), 1L);
		Assertions.assertEquals(50D, statCopy.getTotal().doubleValue());
		Assertions.assertEquals(2L, statCopy.getCount());
		Assertions.assertEquals(25D, statCopy.getAverage().doubleValue());
	}
}