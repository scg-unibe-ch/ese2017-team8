package main.logistics.presentation.controllers;

import main.common.data.models.Delivery;
import main.common.data.models.Parcel;
import main.common.data.repositories.DeliveryRepo;
import main.common.data.repositories.ParcelStatRepo;
import main.logistics.business.LogisticsMainUseCases;
import main.logistics.presentation.viewmodels.DriverListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LogisticsCanceledViewController {

	@Autowired
	public LogisticsMainUseCases interactor;

	@RequestMapping(value="/canceled", method=RequestMethod.GET)
	public String deliveryForm(Model model) {
		return "canceled";
	}

	@ModelAttribute("getCanceledParcelList")
	public List<Parcel> getCanceledParcelList() {
		return interactor.getCanceledParcels();
	}

	//for reactivate handling see LogisticsArchiveViewController
}
