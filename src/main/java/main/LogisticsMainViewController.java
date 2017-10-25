package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LogisticsMainViewController {

	@Autowired
	public DeliveryRepo deliveryRepo;

	@Autowired
	public ParcelRepo parcelRepo;

	//just an example
	Parcel example1 = new Parcel(2.0, 10.0, 20.0, 2.0, false, false, null);
	Parcel example2 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Bombe");

	/**
	 * handles a form with post method
	 * @return direction of post output
	 */
	@RequestMapping(value="/logistics", method=RequestMethod.POST)
	public String deliverySubmit(@ModelAttribute("assignDriver") AssignDriverModel viewModel, BindingResult bindingResult, Model model) {
		System.out.println("Delivery submitted");
		System.out.println(viewModel.driverName);
		System.out.println(viewModel.parcelId);
		return "result";
	}


	@RequestMapping(value="/logistics", method=RequestMethod.GET)
	public String deliveryForm(Model model) {
		return "logistics";
	}

	/**
	 * Is neccessary for the thymeleaf table representation of the data.
	 *
	 * @return list with all orders
	 */
	@ModelAttribute("getParcelList")
	public List<Parcel> getParcelList() {
		//this part is only here to have some examples already in the list

		parcelRepo.save(example1);
		parcelRepo.save(example2);

		return this.parcelRepo.findAll();
	}

	@ModelAttribute("assignDriver")
	public AssignDriverModel getModel() {
		return new AssignDriverModel();
	}

	/**
	 * At the moment is used to insert some hardcoded drivers.
	 * TODO: implement list of real drivers
	 * @return List with all hardcoded drivers.
	 */
	@ModelAttribute("getDriverList")
	public List<String> getDriverList() {
		List<String> drivers = new ArrayList<>();
		drivers.add("Hans Nötig");
		drivers.add("Donald Duck");
		drivers.add("Christiane T");
		return drivers;
	}
}
