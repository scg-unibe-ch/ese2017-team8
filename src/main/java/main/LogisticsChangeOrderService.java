package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class LogisticsChangeOrderService {

	@Autowired
	public ParcelRepo parcelRepo;

	public Parcel getParcelById(Long parcelId) {
		return this.parcelRepo.getParcelById(3L);
	}
}
