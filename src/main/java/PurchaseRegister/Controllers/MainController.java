package PurchaseRegister.Controllers;

import PurchaseRegister.DataModels.*;
import PurchaseRegister.Storage.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static PurchaseRegister.Storage.Data.purchaseList;

/**
 * <b>Controller class for handling Purchase Register requests.</b>
 * @see #mainStart(Model)
 * @see #mainRestart(Model)
 * @see #mainDeleteSelected(MainCube, Model)
 * @see #mainReload(MainCube, Model)
 * @see #mainSave(MainCube, Model)
 * @see #mainNew(MainCube, Model)
 * @see #mainView(MainCube, Model)
 * @see #mainStats(MainCube, Model)
 * @see #itemRegister(ItemCube, Model)
 * @see #itemModify(ItemCube, Model)
 * @see #itemDelete(ItemCube, Model)
 * @author Laszlo Grimm
 * @since 11-11-2022
 */
@Controller
public class MainController {

	/**
	 * <b>GET mapper for start Main template.</b>
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@GetMapping("/")
	public String mainStart(Model model) {
		model.addAttribute(new MainCube(purchaseList, null, "Welcome!"));
		return "Main";
	}

	/**
	 * <b>POST mapper for re-start Main template.</b>
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/")
	public String mainRestart(Model model) {
		model.addAttribute(new MainCube(purchaseList, null, "Welcome!"));
		return "Main";
	}

	/**
	 * <b>POST mapper for selectAll submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
/*
	@PostMapping("/selectAll")
	public String mainSelectAll(@ModelAttribute MainCube mainCube, Model model) {
		model.addAttribute(new MainCube(purchaseList, purchaseList.count() + " purchase(s) have been selected."));
		return "Main";
	}
*/

	/**
	 * <b>POST mapper for selectNone submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
/*
	@PostMapping("/selectNone")
	public String mainSelectNone(@ModelAttribute MainCube mainCube, Model model) {
		model.addAttribute(new MainCube(purchaseList, "0 purchase has been selected."));
		return "Main";
	}
*/

	/**
	 * <b>POST mapper for selectInvert submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
/*
	@PostMapping("/selectInvert")
	public String mainSelectInvert(@ModelAttribute MainCube mainCube, Model model) {
//		purchaseList.select(PurchaseList.SelectionMode.INVERT);
//		model.addAttribute(new MainCube(purchaseList, purchaseList.countSelected() + " purchase(s) have been selected."));
		return "Main";
	}
*/

	/**
	 * <b>POST mapper for deleteSelected submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/deleteSelected")
	public String mainDeleteSelected(@ModelAttribute MainCube mainCube, Model model) {
		if (mainCube == null || mainCube.getMainCubeSelectionIndexes() == null || mainCube.getMainCubeSelectionIndexes().isEmpty()) {
			model.addAttribute(new MainCube(purchaseList, null, "Purchase(s) have NOT been deleted!"));
			return "Main";
		}
		int deleted = 0;
		mainCube.getMainCubeSelectionIndexes().sort(Collections.reverseOrder());
		for (int itemIndex = 0, size = mainCube.getMainCubeSelectionIndexes().size(); itemIndex < size; itemIndex++) {
			purchaseList.delete(itemIndex);
			deleted++;
		}
		model.addAttribute(new MainCube(purchaseList, null, deleted + " purchase(s) have been deleted."));
		return "Main";
	}

	/**
	 * <b>POST mapper for reload submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/reload")
	public String mainReload(@ModelAttribute MainCube mainCube, Model model) {
		String message = Data.load() ? "Purchases have been successfully reloaded." : "Purchases have NOT been reloaded.";
		model.addAttribute(new MainCube(purchaseList, null, message));
		return "Main";
	}

	/**
	 * <b>POST mapper for save submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/save")
	public String mainSave(@ModelAttribute MainCube mainCube, Model model) {
		String message = Data.save() ? "Purchases have been successfully saved." : "Purchases have NOT been saved!";
		model.addAttribute(new MainCube(purchaseList, mainCube.getMainCubeSelectionIndexes(), message));
		return "Main";
	}

	/**
	 * <b>POST mapper for new item submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/newItem")
	public String mainNew(@ModelAttribute MainCube mainCube, Model model) {
		model.addAttribute(new ItemCube(-1, new Purchase(null, Purchase.PurchaseType.CARD, 0, ""), "Enter a new Purchase here."));
		return "Item";
	}

	/**
	 * <b>POST mapper for view item submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/viewItem")
	public String mainView(@ModelAttribute MainCube mainCube, Model model) {
		if (mainCube == null || mainCube.getMainCubeSelectionIndexes() == null || mainCube.getMainCubeSelectionIndexes().size() != 1) {
			model.addAttribute(new MainCube(purchaseList, null, "Please select 1 Purchase which you would like to view."));
			return "Main";
		}
		model.addAttribute(new ItemCube(mainCube.getMainCubeSelectionIndexes().get(0), purchaseList.get(mainCube.getMainCubeSelectionIndexes().get(0)), "Modify or delete a new Purchase here."));
		return "Item";
	}

	/**
	 * <b>POST mapper for statistics submit on Main template.</b>
	 * @param mainCube	MainCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/stats")
	public String mainStats(@ModelAttribute MainCube mainCube, Model model) {
		if (purchaseList.count() == 0) {
			model.addAttribute(new MainCube(purchaseList, null, "You need at least 1 Purchase for creating statistics."));
			return "Main";
		}
		model.addAttribute(new MainCube(purchaseList, null, "THIS WILL BRING YOU TO STATS LATER."));
		//TODO Stats
		return "Main";
	}

	/**
	 * <b>POST mapper for register item submit on Item template.</b>
	 * @param itemCube	ItemCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/registerItem")
	public String itemRegister(@ModelAttribute ItemCube itemCube, Model model) {
		if (itemCube == null || itemCube.getPurchaseDate() == null || itemCube.getPurchaseType() == null || itemCube.getPurchaseValue() == null) {
			model.addAttribute(new MainCube(purchaseList, null, "The new Purchase has NOT been registered!"));
			return "Main";
		}
		purchaseList.add(new Purchase(itemCube.getPurchaseDate(), itemCube.getPurchaseType(), itemCube.getPurchaseValue(), itemCube.getPurchaseDescription()));
		model.addAttribute(new MainCube(purchaseList, List.of(purchaseList.count() - 1), "The new Purchase has been registered."));
		return "Main";
	}

	/**
	 * <b>POST mapper for modify item submit on Item template.</b>
	 * @param itemCube	ItemCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/modifyItem")
	public String itemModify(@ModelAttribute ItemCube itemCube, Model model) {
		if (itemCube == null || itemCube.getPurchaseDate() == null || itemCube.getPurchaseType() == null || itemCube.getPurchaseValue() == null || itemCube.getItemIndex() == null) {
			model.addAttribute(new MainCube(purchaseList, null, "The Purchase has NOT been modified!"));
			return "Main";
		}
		purchaseList.modify(itemCube.getItemIndex(), new Purchase(itemCube.getPurchaseDate(), itemCube.getPurchaseType(), itemCube.getPurchaseValue(), itemCube.getPurchaseDescription()));
		model.addAttribute(new MainCube(purchaseList, List.of(itemCube.getItemIndex()), "The Purchase has been modified."));
		return "Main";
	}

	/**
	 * <b>POST mapper for delete item submit on Item template.</b>
	 * @param itemCube	ItemCube of received data from submitter previous page.
	 * @param model		Model of data sent to aimed page.
	 * @return			String of file name of aimed page.
	 */
	@PostMapping("/deleteItem")
	public String itemDelete(@ModelAttribute ItemCube itemCube, Model model) {
		if (itemCube == null || itemCube.getItemIndex() == null) {
			model.addAttribute(new MainCube(purchaseList, null, "The Purchase has NOT been deleted!"));
			return "Main";
		}
		purchaseList.delete(itemCube.getItemIndex());
		model.addAttribute(new MainCube(purchaseList, null, "The Purchase has been deleted."));
		return "Main";
	}
}
