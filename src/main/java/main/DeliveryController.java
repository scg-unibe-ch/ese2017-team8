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

@Controller
public class DeliveryController {

    @Autowired
    public DeliveryRepo deliveryRepo;

    //just an example
    Delivery example1 = new Delivery(new Date(117,9,1),new Date(117,9,1),45L,15L, Delivery.Status.unscheduled, "Christiane T");
    Delivery example2 = new Delivery(new Date(117,10,1),new Date(117,9,1),33L,51L, Delivery.Status.scheduled, "Donald Duck");
    
    /**
     * is neccessary for the thymeleaf table representation of the data
     * @return list with all deliveries
     */
    @RequestMapping(value="/driver", method= RequestMethod.GET)
    @ModelAttribute("allDelivery")
    public List<Delivery> showAllDelivery() {
        //this part is only here to have some examples already in the list
        deliveryRepo.save(example1);
        deliveryRepo.save(example2);
        return this.deliveryRepo.findAll();
    }

    @ModelAttribute("allDeliveryDriver")
    public List<Delivery> showAllDeliveryDriver() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println(currentUserName);
        //TODO: Get the filter working
        return this.deliveryRepo.findByDriver(currentUserName);
    }
}
