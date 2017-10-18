package main;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * handles the order creation form
 */

@Controller
public class OrderController {

    @Autowired
    public OrderRepo orderRepo;

    //just an example
    Order bsp1 = new Order("Kreissäge",2,32, new Date(102,11,4),3L);

    @RequestMapping(value="/logistics", method= RequestMethod.GET)
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        orderRepo.save(bsp1);
        return "logistics";
    }

    /**
     * handles a form with post method
     * @return direction of post output
     */
    @RequestMapping(value="/logistics", method=RequestMethod.POST)
    public String orderSubmit(@ModelAttribute Order order, Model model) {
        model.addAttribute("order", order);
        orderRepo.save(order);
        System.out.println(orderRepo.findAll());
        return "result";
    }

    /**
     * is neccessary for the thymeleaf table representation of the data
     * @return list with all orders
     */
    @ModelAttribute("allOrders")
    public List<Order> showAllOrders() {
        return this.orderRepo.findAll();
    }
}