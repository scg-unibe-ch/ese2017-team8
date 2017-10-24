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
 * This class handles the delivery controlling.
 * It handles the creating of the input blank in the @link{parcelForm} method.
 * Then it controls the submit function with the @link{parcelSubmit} method.
 * Additional it also handles the search for all parcels and drivers,
 * with the @link{showAllParcel} and @link{showAllDrivers} methods.
 *
 * @author Team8
 * @version 1.0
 */

@Controller
public class ParcelController {

    @Autowired
    public ParcelRepo repository;

    //just an example
    Parcel example1 = new Parcel(2.0, 10.0, 20.0, 2.0, false, false, null);
    Parcel example2 = new Parcel(10.0, 20.0, 30.0, 5.2, true, false, "Bombe");

    /**
     * Creates the empty form for entering the parcels specs.
     *
     * @param model
     * @return String logistics
     */

    @RequestMapping(value="/logistics", method= RequestMethod.GET)
    @ModelAttribute("package")
    public String parcelForm(Model model) {
        model.addAttribute("parcel", new Parcel());
        return "logistics";
    }

    /**
     * Handles a form with post method.
     *
     * @return direction of post output
     */
    @RequestMapping(value="/logistics", method=RequestMethod.POST)
    public String parcelSubmit(@ModelAttribute("parcel") Parcel parcel, BindingResult bindingResult, Model model) {
        repository.save(parcel);
        return "result";
    }

    /**
     * Is neccessary for the thymeleaf table representation of the data.
     *
     * @return list with all orders
     */
    @ModelAttribute("allParcel")
    public List<Parcel> showAllParcel() {
        //this part is only here to have some examples already in the list
        repository.save(example1);
        repository.save(example2);
        return this.repository.findAll();
    }

    /**
     * At the moment is used to insert some hardcoded drivers.
     * TODO: implement list of real drivers
     * @return List with all hardcoded drivers.
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