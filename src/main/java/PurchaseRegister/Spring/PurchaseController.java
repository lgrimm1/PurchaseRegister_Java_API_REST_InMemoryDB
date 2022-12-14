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
	@ResponseBody
	public Purchase getPurchaseById(@PathVariable long id) {
		return service.getPurchaseById(id);
	}

	@PutMapping("/newPurchase")
	@ResponseBody
	public Long addNewPurchase(@RequestBody Purchase newPurchase) {
		System.out.println("----- Entered into PurchaseController#addNewPurchase()");
		if (newPurchase.getPurchaseDate() == null) {
			System.out.println(
					newPurchase.getPurchaseId() + " | " +
					"null | " +
					newPurchase.getPurchaseType() + " | " +
					newPurchase.getPurchaseValue().doubleValue() + " | " +
					newPurchase.getPurchaseDescription());
		}
		else {
			System.out.println(
					newPurchase.getPurchaseId() + " | " +
					newPurchase.getPurchaseDate().getYear() + "-" +
					newPurchase.getPurchaseDate().getMonthValue() + "-" +
					newPurchase.getPurchaseDate().getDayOfMonth()+ " | " +
					newPurchase.getPurchaseType() + " | " +
					newPurchase.getPurchaseValue().doubleValue() + " | " +
					newPurchase.getPurchaseDescription());
		}
		long result = service.addNewPurchase(newPurchase);
		System.out.println("Result: " + result);
//		return service.addNewPurchase(newPurchase);
		return result;
	}

	@PutMapping("/purchase")
	@ResponseBody
	public boolean modifyPurchase(@RequestBody Purchase newPurchase) {
		return service.modifyPurchase(newPurchase);
	}

	@DeleteMapping("/purchase/{id}")
	@ResponseBody
	public boolean deletePurchase(@PathVariable long id) {
		return service.deletePurchase(id);
	}

	@GetMapping("/purchases")
	@ResponseBody
	public List<Purchase> getPurchases() {
		return service.getPurchases();
	}

	@DeleteMapping("/purchases")
	@ResponseBody
	public List<Long> deletePurchases(@RequestBody List<Long> idList) {
		return service.deletePurchases(idList);
	}

	@GetMapping("/count")
	@ResponseBody
	public int countPurchases() {
		return service.countPurchases();
	}

	@GetMapping("/stat/annual")
	@ResponseBody
	public List<StatAnnualTransfer> statAnnual() {
		return service.generateAnnualStat();
	}

	@GetMapping("/stat/months")
	@ResponseBody
	public List<StatMonthlyTransfer> statMonthly() {
		return service.generateMonthlyStat();
	}

	@GetMapping("/stat/full")
	@ResponseBody
	public StatFullTransfer statFull() {
		return service.generateFullStat();
	}
}
