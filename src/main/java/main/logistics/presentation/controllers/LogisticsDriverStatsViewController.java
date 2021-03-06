package main.logistics.presentation.controllers;

import main.common.data.repositories.ParcelStatRepo;
import main.common.data.repositories.UserRepo;
import main.logistics.business.LogisticsMainUseCases;
import main.logistics.presentation.viewmodels.DriverListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ModelAttribute("attemptedParcelCount")
    public Long attemptedParcelCount(@RequestParam Long driverId, Model map){
        return parcelStatRepo.countAttemptedParcelsForDriver(driverId);
    }

    @ModelAttribute("deliveredParcelCount")
    public Long deliveredParcelCount(@RequestParam Long driverId, Model map){
        return parcelStatRepo.countDeliveredParcelsForDriver(driverId);
    }

    @ModelAttribute("scheduledParcelCount")
    public Long scheduledParcelCount(@RequestParam Long driverId, Model map){
        return parcelStatRepo.countScheduledParcelsForDriver(driverId);
    }

    @RequestMapping
    public String handleEmployeeRequestByArea() {
        return "driverstats";
    }
}
