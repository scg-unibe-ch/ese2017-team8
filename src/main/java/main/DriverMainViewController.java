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
import java.util.*;

@Controller
public class DriverMainViewController {

	@Autowired
	public DeliveryRepo deliveryRepo;

	@Autowired
	public ParcelRepo parcelRepo;

	@Autowired
	public UserRepo userRepo;

	@RequestMapping(value="/driver", method= RequestMethod.POST)
	public String deliverySubmit(@ModelAttribute("assignDelivery") Delivery delivery, BindingResult bindingResult, Model model) {
		Delivery hans = deliveryRepo.findByParcelId(delivery.getParcelId());
		hans.setSequence(delivery.getSequence());
		hans.setStatus(delivery.getStatus());
		deliveryRepo.save(hans);
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
			rowModel.setSequence(del.getSequence());

			Parcel parcel = parcelRepo.findOne(del.getParcelId());

			// es kann sein, dass Paket gelöscht wurde
			if (parcel != null) {
				rowModel.setComment(parcel.getComment());
				rowModel.setZeitfenster(parcel.getZeitfenster());
				rowModel.setDangerous(parcel.isDangerous());
				rowModel.setFragile(parcel.isFragile());
				rowModel.setRecipient(parcel.getRecipient());
				rowModel.setAddress(parcel.getAddress());
				rowModel.setPlz(parcel.getPlz());
				rowModel.setCity(parcel.getCity());

				viewModel.add(rowModel);
			}
		}

		Collections.sort(viewModel, new Comparator<DriverDeliveryListModel>(){
			public int compare(DriverDeliveryListModel o1, DriverDeliveryListModel o2){
				return o1.getSequence() - o2.getSequence();
			}
		});

		return viewModel;

//		return this.deliveryRepo.findByDriverId(userRepo.findByUsername(currentUserName).getId());
	}

	@ModelAttribute("possibleParcelStatusDriver")
	public List<Delivery.Status> possibleParcelStatusDriver(){
		List<Delivery.Status> driverStatus = new ArrayList<>();
		driverStatus.add(Delivery.Status.attempted);
		driverStatus.add(Delivery.Status.delivered);
		return driverStatus;
	}

	@ModelAttribute("loggedInUser")
	public String returnLoggedInName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	@ModelAttribute("attempted")
	public Delivery.Status returnStatusAttempted(){
		return Delivery.Status.attempted;
	}

	@ModelAttribute("delivered")
	public Delivery.Status returnStatusDelivered(){
		return Delivery.Status.delivered;
	}
}
