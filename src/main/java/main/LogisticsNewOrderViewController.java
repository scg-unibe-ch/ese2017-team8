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
public class LogisticsNewOrderViewController {

	@Autowired
	public ParcelRepo parcelRepo;

	@Autowired
	public ParcelStatRepo parcelStatRepo;

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

		//status change
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//if no user is authenticated
		String currentUserName = "kein User";
		if (authentication != null) {
			currentUserName = authentication.getName();
		}
		ParcelStat newParcelStat = new ParcelStat(parcel.getId(), Delivery.Status.unscheduled, currentUserName, null);
		parcelStatRepo.save(newParcelStat);
		return "redirect:/logistics";
	}
}
