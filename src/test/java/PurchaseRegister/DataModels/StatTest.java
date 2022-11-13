package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

class StatTest {

	@Test
	void constructStat() {
		Stat stat = new Stat(50d, 2);
		Assertions.assertEquals(50d, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(25d, stat.getAverage());

		stat = new Stat(-5d, 5);
		Assertions.assertEquals(-5d, stat.getTotal());
		Assertions.assertEquals(5, stat.getCount());
		Assertions.assertEquals(-1d, stat.getAverage());
	}

	@Test
	void copy() {
		Stat stat = new Stat(50d, 2);
		Stat statCopy = stat.deepCopy();
		stat = new Stat(2d, 1);
		Assertions.assertEquals(50d, statCopy.getTotal());
		Assertions.assertEquals(2, statCopy.getCount());
		Assertions.assertEquals(25d, statCopy.getAverage());
	}
}