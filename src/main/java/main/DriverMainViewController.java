package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class DriverMainViewController {

	@Autowired
	public DeliveryRepo deliveryRepo;

	//just an example
	Delivery example1 = new Delivery(null, LocalDate.of(2017, 9, 1),45L,15L, Delivery.Status.unscheduled, "Christiane T");
	Delivery example2 = new Delivery(null, LocalDate.of(2017,9,2),33L,51L, Delivery.Status.scheduled, "Donald Duck");

	/**
	 * is neccessary for the thymeleaf table representation of the data
	 * @return list with all deliveries for current driver
	 */
	@RequestMapping(value="/driver")
	@ModelAttribute("allDeliveryDriver")
	public List<Delivery> showAllDeliveryDriver() {
		//this part is only here to have some examples already in the list
		deliveryRepo.save(example1);
		deliveryRepo.save(example2);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		System.out.println(currentUserName);
		return this.deliveryRepo.findByDriver(currentUserName);
	}
}
