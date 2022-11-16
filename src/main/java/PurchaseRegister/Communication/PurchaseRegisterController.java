package PurchaseRegister.Communication;

import PurchaseRegister.DataModels.*;
import PurchaseRegister.Helpers.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

/**
 * Controller class for handling Purchase Register requests.
 * @author Laszlo Grimm
 */
@Controller
public class PurchaseRegisterController {

	@GetMapping("/purchase/{id}")
	@ResponseBody
	public Purchase getPurchaseById(@PathVariable String id) {
		return ControllerHelper.getPurchaseByIdHelper(id);
	}

	@PutMapping("/purchase")
	@ResponseBody
	public Integer addNewPurchase(@RequestBody LocalDate date, @RequestBody Purchase.PurchaseType type, @RequestBody Double value, @RequestBody String description) {
		return ControllerHelper.addNewPurchaseHelper(date, type, value, description);
	}

	@PutMapping("/purchase/{id}")
	@ResponseBody
	public Boolean modifyPurchase(@PathVariable String id, @RequestBody LocalDate date, @RequestBody Purchase.PurchaseType type, @RequestBody Double value, @RequestBody String description) {
		return ControllerHelper.modifyPurchaseHelper(id, date, type, value, description);
	}

	@DeleteMapping("/purchase/{id}")
	@ResponseBody
	public Boolean deletePurchase(@PathVariable String id) {
		return ControllerHelper.deletePurchaseHelper(id);
	}

	@GetMapping("/purchases")
	@ResponseBody
	public List<Purchase> getPurchases() {
		return ControllerHelper.getPurchasesHelper();
	}

	@DeleteMapping("/purchases")
	@ResponseBody
	public List<Integer> deletePurchases(@RequestBody List<Integer> idList) {
		return ControllerHelper.deletePurchasesHelper(idList);
	}
}

//TODO test with writing curl commands into a file.
//TODO write endpoints based on JSON.txt
