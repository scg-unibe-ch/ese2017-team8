package main.logistics.presentation.controllers;

import main.common.business.logging.parcel.LogParcelEventUseCases;
import main.common.data.models.Delivery;
import main.common.data.models.Parcel;
import main.common.data.repositories.DeliveryRepo;
import main.logistics.business.LogisticsMainUseCases;
import main.logistics.presentation.viewmodels.AssignDriverModel;
import main.logistics.presentation.viewmodels.DriverListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LogisticsArchiveViewController {

	@Autowired
	public LogisticsMainUseCases interactor;

	@Autowired
	public DeliveryRepo deliveryRepo;


	@RequestMapping(value="/archive", method=RequestMethod.GET)
	public String deliveryForm(Model model) {
		return "archive";
	}

	@ModelAttribute("getArchivedParcelList")
	public List<Parcel> getArchivedParcelList() {
		return interactor.getArchivedParcels();
	}

	/**
	 * reactivates parcel, changes status to unscheudled and save stat
	 * @param id of parcel over url
	 * @return redirects to logistics and reloads the page
	 */
	@RequestMapping(value="/reactivate", method=RequestMethod.GET)
	public String reactivateParcel(@RequestParam String id, Model map) {
		Long parcelId = Long.parseLong(id, 10);
		Delivery del = deliveryRepo.findByParcelId(parcelId);
		interactor.didReactivateParcel(parcelId, del.getId());

		del.setStatus(Delivery.Status.unscheduled);
		return "redirect:logistics";
	}
}
