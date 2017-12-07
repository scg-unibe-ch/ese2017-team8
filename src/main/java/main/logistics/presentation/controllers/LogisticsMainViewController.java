package main.logistics.presentation.controllers;

import main.logistics.business.LogisticsMainUseCases;
import main.common.data.models.Parcel;
import main.logistics.presentation.viewmodels.AssignDriverModel;
import main.logistics.presentation.viewmodels.DriverListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class LogisticsMainViewController {

	@Autowired
	public LogisticsMainUseCases interactor;


	/**
	 * handles a form with post method
	 * @return direction of post output
	 */
	@RequestMapping(value="/logistics", method=RequestMethod.POST)
	public String deliverySubmit(@ModelAttribute("assignDriver") AssignDriverModel viewModel, BindingResult bindingResult, Model model) {
		interactor.didSubmitDelivery(viewModel.getDriver(), viewModel.getParcelId());
		return "redirect:logistics";
	}


	@RequestMapping(value="/logistics", method=RequestMethod.GET)
	public String deliveryForm(Model model) {
		return "logistics";
	}

	/**
	 * Is necessary for the thymeleaf table representation of the data.
	 * @return list with all orders
	 */
	@ModelAttribute("getParcelList")
	public List<Parcel> getParcelList() {
		return interactor.getActiveParcels();
	}

	/**
	 * At the moment is used to insert some hardcoded drivers.
	 * @return List with all hardcoded drivers.
	 */
	@ModelAttribute("getDriverList")
	public List<DriverListModel> getDriverList(Model model) {
		return interactor.getDriversList();
	}
}
