package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * handles the order creation form
 *
 * @author samuel
 */

@Controller
public class ParcelController {

    @Autowired
    public ParcelRepo repository;

    //just an example
    Parcel example1 = new Parcel(2.0, 10.0, 20.0, 2.0, false, false, null);
    Parcel example2 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Bombe");

    @RequestMapping(value="/neworder", method= RequestMethod.GET)
    @ModelAttribute("package")
    public String parcelForm(Model model) {
        model.addAttribute("parcel", new Parcel());
        return "neworder";
    }

    /**
     * handles a form with post method
     * @return direction of post output
     */
    @RequestMapping(value="/neworder", method=RequestMethod.POST)
    public String parcelSubmit(@ModelAttribute("parcel") Parcel parcel, BindingResult bindingResult, Model model) {
        repository.save(parcel);
        return "redirect:/logistics";
    }

    /**
     * is neccessary for the thymeleaf table representation of the data
     * @return list with all orders
     */
    @RequestMapping("/logistics")
    @ModelAttribute("allParcel")
    public List<Parcel> showAllParcel() {
        //this part is only here to have some examples already in the list
        repository.save(example1);
        repository.save(example2);
        System.out.println(example1);
        return this.repository.findAll();
    }

    /**
     * TODO: implement list of real drivers
     * @return list with all drivers
     */
    @ModelAttribute("allDrivers")
    public List<String> showAllDrivers() {
        List<String> drivers = new ArrayList<>();
        drivers.add("Hans Nötig");
        drivers.add("Donald Duck");
        drivers.add("Christiane T");
        return drivers;
    }
}