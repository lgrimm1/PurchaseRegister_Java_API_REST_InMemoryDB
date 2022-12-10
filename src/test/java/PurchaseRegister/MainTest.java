package PurchaseRegister;

import PurchaseRegister.Spring.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainTest {

	@Autowired
	private PurchaseController purchaseController;

	@Test
	public void contextLoads() throws Exception {
		Assertions.assertNotNull(purchaseController);
	}
}
