package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FinishTourInteractor {
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
