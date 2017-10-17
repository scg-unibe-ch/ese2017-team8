package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    public OrderRepo orders;

    Date now = new Date();

    Order hans = new Order("Kreissäge",2,32, now,3L);


    @GetMapping("/logistics")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "logistics";
    }

    @PostMapping("/logistics")
    public String orderSubmit(@ModelAttribute Order order) {
        orders.save(order);
        orders.save(hans);
        System.out.println(orders.findAll());
        return "result";
    }


}