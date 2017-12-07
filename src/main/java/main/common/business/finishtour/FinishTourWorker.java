package main.common.business.finishtour;

import main.common.data.repositories.DeliveryRepo;
import main.common.data.repositories.ParcelRepo;
import main.common.data.repositories.UserRepo;
import main.common.data.models.Delivery;
import main.common.data.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FinishTourWorker implements FinishTourUseCases {
	@Autowired
	DeliveryRepo deliveryRepo;

	@Autowired
	ParcelRepo parcelRepo;

	@Autowired
	UserRepo userRepo;

	public void finishTourForDriver(User driver) {
		List<Delivery> assignedDeliveries = deliveryRepo.findByDriverId(driver.getId());

		for (Delivery d : assignedDeliveries) {
			switch (d.getStatus()) {
				case attempted:
					d.setStatus(Delivery.Status.unscheduled);
					d.setDriverId(null);
					d.setScheduledDate(null);
					d.setSequence(0);
					break;
				case delivered:
					d.setActualDate(LocalDate.now());
					d.setStatus(Delivery.Status.archived);
					break;
			}
			this.deliveryRepo.save(d);
		}
	}
}