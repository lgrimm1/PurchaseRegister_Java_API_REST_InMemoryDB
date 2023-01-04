package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.time.*;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PurchaseService purchaseService;

	Purchase purchaseWithWrongData = new Purchase(
			5L,
			null,
			Purchase.PurchaseType.CASH,
			12.24D,
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
			12.24D,
			"abc"
	);
	Purchase purchaseWithRightData2 = new Purchase(
			3L,
			LocalDate.of(2022, 12, 17),
			Purchase.PurchaseType.INTERNET,
			36.48D,
			"xyz"
	);
	String purchaseWithRightDataJSON =
			"{\"purchaseId\":5," +
			"\"purchaseDate\":\"2010-06-03\"," +
			"\"purchaseType\":\"CASH\"," +
			"\"purchaseValue\":12.24," +
			"\"purchaseDescription\":\"abc\"}";
	String purchaseWithRightData2JSON =
			"{\"purchaseId\":3," +
			"\"purchaseDate\":\"2022-12-17\"," +
			"\"purchaseType\":\"INTERNET\"," +
			"\"purchaseValue\":36.48," +
			"\"purchaseDescription\":\"xyz\"}";
	String statAnnualJSON =
			"[" +
					"{\"year\":2010,\"total\":33.0,\"count\":3,\"average\":11.0}," +
					"{\"year\":2020,\"total\":22.0,\"count\":11,\"average\":2.0}" +
			"]";
	String statMonthlyJSON =
			"[" +
					"{\"year\":2010,\"month\":6,\"total\":33.0,\"count\":3,\"average\":11.0}," +
					"{\"year\":2020,\"month\":3,\"total\":22.0,\"count\":11,\"average\":2.0}" +
			"]";
	String statFullJSON = "{\"total\":33.0,\"count\":3,\"average\":11.0}";

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
				.andExpect(content().string("true"));
	}

	@Test
	void deletePurchaseWithNoResult() throws Exception {
		when(purchaseService.deletePurchase(3L))
				.thenReturn(false);
		mockMvc
				.perform(delete("/api/v1/purchase/3")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("false"));
	}

	@Test
	void deletePurchaseWithResult() throws Exception {
		when(purchaseService.deletePurchase(3L))
				.thenReturn(true);
		mockMvc
				.perform(delete("/api/v1/purchase/3")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().string("true"));
	}

	@Test
	void getPurchases() throws Exception {
		List<Purchase> purchases = List.of(purchaseWithRightData, purchaseWithRightData2);
		String purchasesJSON = "[" + purchaseWithRightDataJSON + "," + purchaseWithRightData2JSON + "]";
		when(purchaseService.getPurchases())
				.thenReturn(purchases);
		mockMvc
				.perform(get("/api/v1/purchases")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(purchasesJSON));
	}

	@Test
	void deletePurchases() throws Exception {
		List<Long> idsToDelete = List.of(3L, 5L, 8L);
		when(purchaseService.deletePurchases(idsToDelete))
				.thenReturn(List.of(3L, 5L));
		mockMvc
				.perform(delete("/api/v1/purchases")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.content("[3,5,8]"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json("[3,5]"));
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

	@Test
	public void generateAnnualStat() throws Exception {
		when(purchaseService.generateAnnualStat())
				.thenReturn(List.of(
						new StatAnnualTransfer(2010, 33D, 3, 11D),
						new StatAnnualTransfer(2020, 22D, 11, 2D)
				));
		mockMvc
				.perform(get("/api/v1/stat/annual")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(statAnnualJSON));
	}

	@Test
	public void generateMonthlyStat() throws Exception {
		when(purchaseService.generateMonthlyStat())
				.thenReturn(List.of(
						new StatMonthlyTransfer(2010, 6, 33D, 3, 11D),
						new StatMonthlyTransfer(2020, 3, 22D, 11, 2D)
				));
		mockMvc
				.perform(get("/api/v1/stat/months")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(statMonthlyJSON));
	}

	@Test
	public void generateFullStat() throws Exception {
		when(purchaseService.generateFullStat())
				.thenReturn(new StatFullTransfer(33D, 3, 11D));
		mockMvc
				.perform(get("/api/v1/stat/full")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(statFullJSON));
	}

}
