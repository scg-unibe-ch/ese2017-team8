package main.logistics.presentation.controllers;

import main.common.data.repositories.ParcelStatRepo;
import main.common.data.repositories.UserRepo;
import main.logistics.business.LogisticsMainUseCases;
import main.logistics.presentation.viewmodels.DriverListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("driverstats")
public class LogisticsDriverStatsViewController {

    @Autowired
    public ParcelStatRepo parcelStatRepo;

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public LogisticsMainUseCases interactor;

    @ModelAttribute("getDriverList")
    public List<DriverListModel> getDriverList(Model model) {
        return interactor.getDriversList();
    }


    @ModelAttribute("parcelCount")
    public int parcelCount(){
        //SQL STUFF
        return 0;
    }

    @RequestMapping
    public String handleEmployeeRequestByArea() {
        return "driverstats";
    }
}
