package PurchaseRegister.Spring;

import PurchaseRegister.DataModels.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.lang.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
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
 * @see #statFull()
 * @author Laszlo Grimm
 */
@RestController
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

	private final PurchaseService service;

	@Autowired
	public PurchaseController(PurchaseService service) {
		this.service = service;
	}

	@GetMapping("/purchase/{id}")
	public Purchase getPurchaseById(@PathVariable long id) {
		return service.getPurchaseById(id);
	}

	@PutMapping("/newPurchase")
	public long addNewPurchase(@RequestBody Purchase newPurchase) {
		return service.addNewPurchase(newPurchase);
	}

	@PutMapping("/purchase")
	public boolean modifyPurchase(@RequestBody Purchase newPurchase) {
		return service.modifyPurchase(newPurchase);
	}

	@DeleteMapping("/purchase/{id}")
	public boolean deletePurchase(@PathVariable long id) {
		return service.deletePurchase(id);
	}

	@GetMapping("/purchases")
	public List<Purchase> getPurchases() {
		return service.getPurchases();
	}

	@DeleteMapping("/purchases")
	public List<Long> deletePurchases(@RequestBody @NonNull List<Long> idList) {
		return service.deletePurchases(idList);
	}

	@GetMapping("/count")
	public int countPurchases() {
		return service.countPurchases();
	}

	@GetMapping("/stat/annual")
	public List<StatAnnualTransfer> statAnnual() {
		return service.generateAnnualStat();
	}

	@GetMapping("/stat/months")
	public List<StatMonthlyTransfer> statMonthly() {
		return service.generateMonthlyStat();
	}

	@GetMapping("/stat/full")
	public StatFullTransfer statFull() {
		return service.generateFullStat();
	}
}
