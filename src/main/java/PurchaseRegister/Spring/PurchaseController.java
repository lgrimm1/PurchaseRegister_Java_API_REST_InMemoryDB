package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller class for handling Purchase Register requests.
 * @see #PurchaseController(PurchaseService)
 * @see #getPurchaseById(long)
 * @see #addNewPurchase(Purchase)
 * @see #modifyPurchase(Purchase)
 * @see #deletePurchase(long)
 * @see #getPurchases()
 * @see #deletePurchases(List)
 * @see #countPurchases()
 * @see #statAnnual()
 * @see #statMonthly()
 * @author Laszlo Grimm
 */
@Controller
public class PurchaseController {

	private final PurchaseService service;

	@Autowired
	public PurchaseController(PurchaseService service) {
		this.service = service;
	}

	@GetMapping(value = "/purchase/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Purchase getPurchaseById(@PathVariable long id) {
		return service.getPurchaseById(id);
	}

	@PutMapping(value = "/newPurchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Long addNewPurchase(@RequestBody Purchase newPurchase) {
		return service.addNewPurchase(newPurchase);
	}

	@PutMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean modifyPurchase(@RequestBody Purchase newPurchase) {
		return service.modifyPurchase(newPurchase);
	}

	@DeleteMapping(value = "/purchase/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean deletePurchase(@PathVariable long id) {
		return service.deletePurchase(id);
	}

	@GetMapping(value = "/purchases", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Purchase> getPurchases() {
		return service.getPurchases();
	}

	@DeleteMapping(value = "/purchases", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Long> deletePurchases(@RequestBody List<Long> idList) {
		return service.deletePurchases(idList);
	}

	@GetMapping(value = "/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int countPurchases() {
		return service.countPurchases();
	}

	@GetMapping(value = "/stat/annual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StatAnnualTransfer> statAnnual() {
		return service.generateAnnualStat();
	}

	@GetMapping(value = "/stat/months", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StatMonthlyTransfer> statMonthly() {
		return service.generateMonthlyStat();
	}
}
