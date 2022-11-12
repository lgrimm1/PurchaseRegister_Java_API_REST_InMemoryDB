package PurchaseRegister.DataModels;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StatTest {

	@Test
	void contructStat() {
		Stat stat = new Stat(50d);
		Assertions.assertEquals(50d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(50d, stat.getAverage());

		stat = new Stat(-5d);
		Assertions.assertEquals(-5d, stat.getTotal());
		Assertions.assertEquals(1, stat.getCount());
		Assertions.assertEquals(-5d, stat.getAverage());
	}

	@Test
	void addToTotal() {
		Stat stat = new Stat(50d);
		stat.addTotal(70d);
		Assertions.assertEquals(120d, stat.getTotal());
		Assertions.assertEquals(2, stat.getCount());
		Assertions.assertEquals(60d, stat.getAverage());

		stat.addTotal(-30d);
		Assertions.assertEquals(90d, stat.getTotal());
		Assertions.assertEquals(3, stat.getCount());
		Assertions.assertEquals(30d, stat.getAverage());
	}
}