package PurchaseRegister.Spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.math.*;
import java.time.*;

@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PurchaseService purchaseService;

/*
	private String objectToJSON(Object o) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e) {

		}
	}
*/

	Purchase purchaseWithWrongData = new Purchase(
			5L,
			null,
			Purchase.PurchaseType.CASH,
			BigDecimal.valueOf(12.24),
			"abc"
	);
	String purchaseWithWrongDataJSON =
			"{\"purchaseId\":5," +
					"\"purchaseDate\":null," +
					"\"purchaseType\":\"CASH\"," +
					"\"purchaseValue\":12.24," +
					"\"purchaseDescription\":\"abc\"}";
	Purchase purchaseWithRightData = new Purchase(
			5L,
			LocalDate.of(2010, 6, 3),
			Purchase.PurchaseType.CASH,
			BigDecimal.valueOf(12.24),
			"abc"
	);
	String purchaseWithRightDataJSON =
			"{\"purchaseId\":5," +
					"\"purchaseDate\":\"2010-06-03\"," +
					"\"purchaseType\":\"CASH\"," +
					"\"purchaseValue\":12.24," +
					"\"purchaseDescription\":\"abc\"}";

	@Test
	public void getPurchaseByIdWithNoResult() throws Exception {
		when(purchaseService.getPurchaseById(5L))
				.thenReturn(null);
		mockMvc.perform(get("/api/v1/purchase/5")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string(""));
	}

	@Test
	public void getPurchaseByIdWithResult() throws Exception {
		when(purchaseService.getPurchaseById(5L))
				.thenReturn(purchaseWithRightData);
		mockMvc.perform(get("/api/v1/purchase/5")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(purchaseWithRightDataJSON));
	}

	@Test
	public void addNewPurchaseWithNoResult() throws Exception {
		when(purchaseService.addNewPurchase(purchaseWithWrongData))
				.thenReturn(Long.MIN_VALUE);
		mockMvc
				.perform(put("/api/v1/newPurchase")
						.content(purchaseWithWrongDataJSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string(String.valueOf(Long.MIN_VALUE)));
	}

	@Test
	public void addNewPurchaseWithResult() throws Exception {
		when(purchaseService.addNewPurchase(purchaseWithRightData))
				.thenReturn(6L);
		mockMvc
				.perform(put("/api/v1/newPurchase")
						.content(purchaseWithRightDataJSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("6"));
	}

	@Test
	void modifyPurchaseWithNoResult() throws Exception {
		when(purchaseService.modifyPurchase(purchaseWithWrongData))
				.thenReturn(false);
		mockMvc
				.perform(put("/api/v1/purchase")
						.content(purchaseWithWrongDataJSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("false"));
	}

	@Test
	void modifyPurchaseWithResult() throws Exception {
		when(purchaseService.modifyPurchase(purchaseWithRightData))
				.thenReturn(true);
		mockMvc
				.perform(put("/api/v1/purchase")
						.content(purchaseWithRightDataJSON)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("true"));
	}

		@Test
	public void getCount() throws Exception {
		when(purchaseService.countPurchases())
				.thenReturn(2);
		this.mockMvc
				.perform(get("/api/v1/count")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("2"));
	}
}
