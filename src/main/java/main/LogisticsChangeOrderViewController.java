package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogisticsChangeOrderViewController {

	@Autowired
	public ParcelRepo parcelRepo;

	@ModelAttribute("parcel")
	public Parcel getParcel(){
		return new Parcel();
	}

	@ModelAttribute("parcelById")
	public Parcel getParcelById(){
		return parcelRepo.getParcelById(3L);
	}

	/**
	 * Creates the empty form for entering the parcels specs.
	 *
	 * @param model
	 * @return String logistics
	 */
	@RequestMapping(value="/changeorder", method=RequestMethod.GET)
	public String parcelForm(Model model) {
		return "changeorder";
	}


	/**
	 * Handles a form with post method.
	 *
	 * @return direction of post output
	 */
	@RequestMapping(value="/changeorder", method=RequestMethod.POST)
	public String parcelChange(@ModelAttribute("assignParcel") Parcel parcel, BindingResult bindingResult, Model model) {
		parcelRepo.save(parcel);
		return "redirect:/logistics";
	}
}
