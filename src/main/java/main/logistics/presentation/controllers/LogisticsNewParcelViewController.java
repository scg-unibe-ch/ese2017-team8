package main.logistics.presentation.controllers;

import main.common.data.models.ParcelStat;
import main.common.data.repositories.ParcelRepo;
import main.common.data.repositories.ParcelStatRepo;
import main.common.data.models.Delivery;
import main.common.data.models.Parcel;
import main.logistics.business.LogisticsNewParcelUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogisticsNewParcelViewController {

	@Autowired
	LogisticsNewParcelUseCases interactor;

	@ModelAttribute("parcel")
	public Parcel getParcel(){
		return new Parcel();
	}

	/**
	 * Creates the empty form for entering the parcels specs.
	 *
	 * @param model
	 * @return String logistics
	 */
	@RequestMapping(value="/neworder", method=RequestMethod.GET)
	public String parcelForm(Model model) {
		return "neworder";
	}

	/**
	 * Handles a form with post method.
	 *
	 * @return direction of post output
	 */
	@RequestMapping(value="/neworder", method=RequestMethod.POST)
	public String parcelSubmit(@ModelAttribute("parcel") Parcel parcel, BindingResult bindingResult, Model model) {
		interactor.didSubmitParcel(parcel);
		return "redirect:/logistics";
	}
}
