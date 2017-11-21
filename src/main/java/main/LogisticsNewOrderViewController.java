package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LogisticsNewOrderViewController {

	@Autowired
	public ParcelRepo parcelRepo;

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
		parcelRepo.save(parcel);
		return "redirect:/logistics";
	}
}
