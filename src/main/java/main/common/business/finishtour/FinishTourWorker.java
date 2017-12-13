package main.common.business.finishtour;

import main.common.business.logging.parcel.LogParcelEventWorker;
import main.common.data.repositories.DeliveryRepo;
import main.common.data.repositories.ParcelRepo;
import main.common.data.repositories.ParcelStatRepo;
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
	ParcelStatRepo parcelStatRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	LogParcelEventWorker interactor;

	public void finishTourForDriver(User driver) {
		List<Delivery> assignedDeliveries = deliveryRepo.findByDriverId(driver.getId());


		for (Delivery d : assignedDeliveries) {
			interactor.logParcelEvent(d.getParcelId(), d.getStatus(),driver.getId(), driver.getId());
			switch (d.getStatus()) {
				case attempted:
					Long anz_versuche = parcelStatRepo.countAttemptedForParcel(d.getParcelId());
					if (anz_versuche > 1) {
						d.setStatus(Delivery.Status.cancelled);
					} else {
						d.setStatus(Delivery.Status.unscheduled);
					}
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
