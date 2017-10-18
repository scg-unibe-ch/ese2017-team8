package main;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    public OrderRepo orderRepo;

    Date now = new Date();

    Order bsp1 = new Order("Kreissäge",2,32, now,3L);

    @RequestMapping(value="/logistics", method= RequestMethod.GET)
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        orderRepo.save(bsp1);
        return "logistics";
    }

    @RequestMapping(value="/logistics", method=RequestMethod.POST)
    public String orderSubmit(@ModelAttribute Order order, Model model) {
        model.addAttribute("order", order);
        orderRepo.save(order);
        System.out.println(orderRepo.findAll());
        return "result";
    }

    @ModelAttribute("allOrders")
    public List<Order> showAllOrders() {
        return this.orderRepo.findAll();
    }
}