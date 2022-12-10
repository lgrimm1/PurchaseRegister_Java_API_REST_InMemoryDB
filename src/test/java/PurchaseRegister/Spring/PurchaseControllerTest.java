package PurchaseRegister.Spring;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.time.*;

@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PurchaseService service;

	@Test
	public void getPurchaseById() throws Exception {
		when(service.getPurchaseById(5))
				.thenReturn(new Purchase(5L, LocalDate.of(2010, 6, 3), Purchase.PurchaseType.CASH, 12.24, "abc"));
		this.mockMvc
				.perform(get("/purchase/5")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("{\"purchaseId\":5,\"purchaseDate\":\"2010-06-03\",\"purchaseType\":\"CASH\",\"purchaseValue\":12.24,\"purchaseDescription\":\"abc\"}"));
	}

	@Test
	public void newPurchase() throws Exception {
		when(service.addNewPurchase(new Purchase(15L, LocalDate.of(2010, 6, 3), Purchase.PurchaseType.CASH, 12.24, "abc")))
				.thenReturn(Long.valueOf(5));
		this.mockMvc
				.perform(put("/newPurchase")
						.content("{\"purchaseId\":15,\"purchaseDate\":\"2010-06-03\",\"purchaseType\":\"CASH\",\"purchaseValue\":12.24,\"purchaseDescription\":\"abc\"}")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("5"));
	}

	@Test
	public void getCount() throws Exception {
		when(service.countPurchases())
				.thenReturn(2);
		this.mockMvc
				.perform(get("/count")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("2"));
	}
}
