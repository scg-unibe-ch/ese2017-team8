package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LogisticsChangeOrderViewController {

	@Autowired
	public ParcelRepo parcelRepo;

	@ModelAttribute("parcel")
	public Parcel getParcel(){
		return new Parcel();
	}

	@ModelAttribute("getAllParcels")
	public List<Parcel> getAllParcels() {
		return parcelRepo.findAll();
	}

	/**
	 * Creates the empty form for entering the parcels specs.
	 *
	 * @param model
	 * @return String changeorder
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
	@RequestMapping(value="/change", method=RequestMethod.POST)
	public String parcelChange(@ModelAttribute("changeParcel") Parcel parcel, BindingResult bindingResult, Model model) {
		parcelRepo.save(parcel);
		return "redirect:/logistics";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String parcelDelete(@ModelAttribute("deleteParcel") Parcel parcel, BindingResult bindingResult, Model model) {
		System.out.println(parcelRepo.deleteById(parcel.getId()));
		return "redirect:/logistics";
	}
}
