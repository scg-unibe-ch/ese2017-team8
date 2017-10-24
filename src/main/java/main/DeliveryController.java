package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * In this class the deliveries are prepared in lists to be later on used.
 * With @link{showAllDelivery} you are able to retrieve the list of all deliveries.
 * With @link{showAllDeliveryDriver} you are able to get the deliveries for a specific driver.
 *
 * @author Team8
 * @version 1.0
 */

@Controller
public class DeliveryController {

    @Autowired
    public DeliveryRepo deliveryRepo;

    public DeliveryRepo getDeliveryRepo() {
        return deliveryRepo;
    }

    //just an example
    Delivery example1 = new Delivery(null, new Date(117,9,1),45L,15L, Delivery.Status.unscheduled, "Christiane T");
    Delivery example2 = new Delivery(null, new Date(117,9,1),33L,51L, Delivery.Status.scheduled, "Donald Duck");
    
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


    /**
     * handles a form with post method
     * @return direction of post output
     */
    /*
    @RequestMapping(value="/logistics", method=RequestMethod.POST)
    public String deliverySubmit(@ModelAttribute("delivery") Delivery delivery, BindingResult bindingResult, Model model) {
        System.out.println(delivery);
        deliveryRepo.save(delivery);
        return "result";
    }


    @ModelAttribute("delivery")
    @RequestMapping(value="/logistics", method=RequestMethod.GET)
    public String deliveryForm(Model model) {
        model.addAttribute("delivery", new Delivery());
        return "logistics";
    }
    */
}
