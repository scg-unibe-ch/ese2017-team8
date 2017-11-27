package main;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
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

	@Autowired
	private FinishTourInteractor finishTourInteractor;

	public static Boolean shouldGenerateExample = true;

	@RequestMapping(value="/driver/delivery", method= RequestMethod.POST)
	public String deliverySubmit(@ModelAttribute("assignDelivery") Delivery delivery, BindingResult bindingResult, Model model) {
		Delivery submittedDelivery = deliveryRepo.findByParcelId(delivery.getParcelId());
		submittedDelivery.setSequence(delivery.getSequence());
		submittedDelivery.setStatus(delivery.getStatus());
		deliveryRepo.save(submittedDelivery);
		return "redirect:/driver";
	}

	@RequestMapping(value="/driver/finishTour", method= RequestMethod.GET)
	public String finishTour() {
		System.out.println("Finished Tour");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();

		finishTourInteractor.finishTourForDriver(this.userRepo.findByUsername(currentUserName));
		return "redirect:/driver";
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

		this.generateExamples();

//		List<Delivery> deliveries = this.deliveryRepo.findByDriverId(userRepo.findByUsername(currentUserName).getId());
		List<Delivery> deliveries = this.deliveryRepo.findByDriverId(userRepo.findByUsername(currentUserName).getId());
//		List<Delivery> deliveries = new ArrayList<Delivery>();

		List<DriverDeliveryListModel> viewModel = new ArrayList<>();

		for (Delivery del : deliveries) {
			DriverDeliveryListModel rowModel = new DriverDeliveryListModel();
			rowModel.setDeliveryId(del.getId());
			rowModel.setParcelId(del.getParcelId());
			rowModel.setStatus(del.getStatus());
			rowModel.setSequence(del.getSequence());

			Parcel parcel = parcelRepo.findOne(del.getParcelId());

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

		Collections.sort(viewModel, new Comparator<DriverDeliveryListModel>(){
			public int compare(DriverDeliveryListModel o1, DriverDeliveryListModel o2){
				return o1.getSequence() - o2.getSequence();
			}
		});

		return viewModel;
	}

	public void generateExamples() {
		if (!shouldGenerateExample) { return; }

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();

		Parcel example1 = new Parcel(2.0, 10.0, 20.0, 2.0, false, false, null, "Bern","6122","Feldstrasse 1", "Fenaco AG","");
		Parcel example2 = new Parcel(10.0, 20.0, 30.0, 5.2, true, true, "Bombe", "Wolhusen","6110","Burgring 88", "Max Muster","ab 17:00");
		Parcel example3 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Nukleares Material", "Schwarzenburg","3120","Genossenweg 2", "Helmut Schmied" ,"");
		Parcel example4 = new Parcel(10.0, 20.0, 30.0, 700, false, false, "Sägemehl", "Beromünster", "6240","Senderstrasse 3a", "Homer Simpson","");

		List<Parcel> parcels = Arrays.asList(example1, example2, example3, example4);
		this.parcelRepo.save(parcels);

		for (Parcel p : parcels) {
			Delivery delivery = new Delivery();
			delivery.setDriverId(this.userRepo.findByUsername(currentUserName).getId());
			delivery.setScheduledDate(LocalDate.now());
			delivery.setParcelId(p.getId());
			delivery.setStatus(Delivery.Status.scheduled);
			this.deliveryRepo.save(delivery);
		}

		shouldGenerateExample = false;
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
