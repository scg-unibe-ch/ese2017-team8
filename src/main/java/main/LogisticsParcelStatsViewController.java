package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
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
	public List<ParcelStat> getAllStatsForParcel() {
		return parcelStatRepo.findByParcelId(10L);
	}

	//necessary to get the parcelstats page
	@RequestMapping(value="/parcelstats", method=RequestMethod.GET)
	public String parcelForm(Model model) {
		return "parcelstats";
	}
}
