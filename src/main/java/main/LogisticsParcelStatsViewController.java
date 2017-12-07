package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("parcelstats")
public class LogisticsParcelStatsViewController {

	@Autowired
	public ParcelRepo parcelRepo;

	@Autowired
	public ParcelStatRepo parcelStatRepo;

	@ModelAttribute("getAllParcels")
	public List<Parcel> getAllParcels() {
		return parcelRepo.findAll();
	}

	@ModelAttribute("getAllStatsForParcel")
	public List<ParcelStat> getAllStatsForParcel(@RequestParam String id, Model map) {
		return parcelStatRepo.findByParcelId(Long.parseLong(id, 10));
	}


	@RequestMapping
	public String handleEmployeeRequestByArea () {
		return "parcelstats";
	}
}
