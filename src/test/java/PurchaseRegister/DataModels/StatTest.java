package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

class StatTest {

	@Test
	void constructStat() {
		Stat stat = new Stat(50D, 2);
		Assertions.assertEquals(50D, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(25D, stat.getAverage());

		stat = new Stat(-5D, 5);
		Assertions.assertEquals(-5D, stat.getTotal());
		Assertions.assertEquals(5, stat.getCount());
		Assertions.assertEquals(-1D, stat.getAverage());
	}

	@Test
	void copy() {
		Stat stat = new Stat(50D, 2);
		Stat statCopy = stat.deepCopy();
		//noinspection UnusedAssignment
		stat = new Stat(2D, 1);
		Assertions.assertEquals(50D, statCopy.getTotal());
		Assertions.assertEquals(2, statCopy.getCount());
		Assertions.assertEquals(25D, statCopy.getAverage());
	}
}