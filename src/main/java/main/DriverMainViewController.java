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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DriverMainViewController {

	@Autowired
	public DeliveryRepo deliveryRepo;

	@Autowired
	public ParcelRepo parcelRepo;

	@Autowired
	public UserRepo userRepo;

	//TODO: here should the magic happen
	@RequestMapping(value="/driver", method= RequestMethod.POST)
	public String deliverySubmit(@ModelAttribute("assignDriver") Delivery delivery, BindingResult bindingResult, Model model) {
		return "redirect:driver";
	}

	/**
	 * is neccessary for the thymeleaf table representation of the data
	 * @return list with all deliveries for current driver
	 */
	@RequestMapping(value="/driver")
	@ModelAttribute("deliveriesForDriver")
	public List<DriverDeliveryListModel> showAllDeliveryDriver() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();

		List<Delivery> deliveries = this.deliveryRepo.findByDriverId(userRepo.findByUsername(currentUserName).getId());
		List<DriverDeliveryListModel> viewModel = new ArrayList<>();

		for (Delivery del : deliveries) {
			DriverDeliveryListModel rowModel = new DriverDeliveryListModel();
			rowModel.setDeliveryId(del.getId());
			rowModel.setParcelId(del.getParcelId());
			rowModel.setStatus(del.getStatus());

			Parcel parcel = parcelRepo.findOne(del.getParcelId());

			rowModel.setComment(parcel.getComment());
			rowModel.setDangerous(parcel.isDangerous());
			rowModel.setFragile(parcel.isFragile());
			rowModel.setAddress(parcel.getAddress());
			rowModel.setPlz(parcel.getPlz());
			rowModel.setCity(parcel.getCity());

			viewModel.add(rowModel);
		}

		return viewModel;

//		return this.deliveryRepo.findByDriverId(userRepo.findByUsername(currentUserName).getId());
	}
}
