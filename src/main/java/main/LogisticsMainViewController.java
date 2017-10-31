package main;

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

	public static int num = 0;

	@Autowired
	public DeliveryRepo deliveryRepo;

	@Autowired
	public ParcelRepo parcelRepo;

	@Autowired
	private CreateDeliveryInteractor createDeliveryInteractor;

	//just an example
	Parcel example1 = new Parcel(2.0, 10.0, 20.0, 2.0, false, false, null);
	Parcel example2 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Bombe");
	Parcel example3 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Nukleares Material");
	Parcel example4 = new Parcel(10.0, 20.0, 30.0, 700, false, false, "Sägemehl");

	/**
	 * handles a form with post method
	 * @return direction of post output
	 */
	@RequestMapping(value="/logistics", method=RequestMethod.POST)
	public String deliverySubmit(@ModelAttribute("assignDriver") AssignDriverModel viewModel, BindingResult bindingResult, Model model) {
		createDeliveryInteractor.createScheduledDelivery(viewModel.driverName, viewModel.parcelId);
		return "logistics";
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
		List<DriverListModel> driverList = new ArrayList<>();

		DriverListModel model1 = new DriverListModel();
		List<Long> parcelIds1 = Arrays.asList(new Long(1), new Long(2), new Long(3));
		model1.setDriverName("Hans Nötig");
		model1.setParcelIds(parcelIds1);


		DriverListModel model2 = new DriverListModel();
		List<Long> parcelIds2 = Arrays.asList(new Long(4));
		model2.setDriverName("Timmy Tester");
		model2.setParcelIds(parcelIds2);

		driverList.add(model1);
		driverList.add(model2);
		return driverList;
	}
}
