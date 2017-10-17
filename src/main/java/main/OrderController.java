package main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OrderController {
    @GetMapping("/logistics")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "logistics";
    }

    @PostMapping("/logistics")
    public String orderSubmit(@ModelAttribute Order order) {
        return "result";
    }
}