package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class DeliveryController {

    @Autowired
    public DeliveryRepo deliveryRepo;

    //just an example
    Delivery example1 = new Delivery(new Date(117,9,1),new Date(117,9,1),1L,1L, Delivery.Status.unscheduled, "Donald Duck");
    Delivery example2 = new Delivery(new Date(117,10,1),new Date(117,9,1),1L,1L, Delivery.Status.scheduled, "Donald Duck");

    /**
     * is neccessary for the thymeleaf table representation of the data
     * @return list with all deliveries
     */
    @ModelAttribute("allDelivery")
    public List<Delivery> showAllDelivery() {
        //this part is only here to have some examples already in the list
        deliveryRepo.save(example1);
        deliveryRepo.save(example2);
        System.out.println(example1);
        return this.deliveryRepo.findAll();
    }
}
