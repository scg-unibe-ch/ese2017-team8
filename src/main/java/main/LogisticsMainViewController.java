package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Driver;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class LogisticsMainViewController {

	public static int num = 0;

	@Autowired
	public DeliveryRepo deliveryRepo;

	@Autowired
	public ParcelRepo parcelRepo;

	@Autowired
	public UserRepo userRepo;

	@Autowired
	private CreateDeliveryInteractor createDeliveryInteractor;

	//just an example
	Parcel example1 = new Parcel(2.0, 10.0, 20.0, 2.0, false, false, null, "Bern","6122","Feldstrasse 1", "");
	Parcel example2 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Bombe", "Wolhusen","6110","Burgring 88", "");
	Parcel example3 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Nukleares Material", "Schwarzenburg","3120","Genossenweg 2", "");
	Parcel example4 = new Parcel(10.0, 20.0, 30.0, 700, false, false, "Sägemehl", "Beromünster", "6240","Senderstrasse 3a", "");

	/**
	 * handles a form with post method
	 * @return direction of post output
	 */
	@RequestMapping(value="/logistics", method=RequestMethod.POST)
	public String deliverySubmit(@ModelAttribute("assignDriver") AssignDriverModel viewModel, BindingResult bindingResult, Model model) {
		User driver = viewModel.getDriver();
		assert(driver.getAuthorities().contains(AuthorityDriver.instance));

		createDeliveryInteractor.createScheduledDelivery(driver, viewModel.getParcelId());
		return "redirect:logistics";
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
		parcelRepo.save(example3);
		parcelRepo.save(example4);
		return this.parcelRepo.findAll();
	}

	/**
	 * At the moment is used to insert some hardcoded drivers.
	 * TODO: implement list of real drivers
	 * @return List with all hardcoded drivers.
	 */
	@ModelAttribute("getDriverList")
	public List<DriverListModel> getDriverList(Model model) {
		List<User> driverList = userRepo.findAllByAuthoritiesContains(AuthorityDriver.instance);

		List<DriverListModel> viewModel = new ArrayList<DriverListModel>();

		for (User u: driverList) {
			DriverListModel driverListModel = new DriverListModel();
			driverListModel.setDriver(u);

			List<Delivery> deliveriesForDriver = deliveryRepo.findByDriverId(u.getId());
			List<Long> parcelIds = deliveriesForDriver.stream().map(Delivery::getParcelId).collect(Collectors.toList());
			driverListModel.setParcelIds(parcelIds);

			viewModel.add(driverListModel);
		}

		return viewModel;
	}
}
