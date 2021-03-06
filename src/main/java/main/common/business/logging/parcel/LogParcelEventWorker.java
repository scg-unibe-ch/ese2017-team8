package main.common.business.logging.parcel;

import groovy.util.logging.Log;
import main.common.data.models.Delivery;
import main.common.data.models.ParcelStat;
import main.common.data.repositories.ParcelStatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogParcelEventWorker implements LogParcelEventUseCases {

	@Autowired
	ParcelStatRepo parcelStatRepo;

	public void logParcelEvent(Long parcelId, Delivery.Status deliveryStatus, Long currentUserId, Long driverId) {
		ParcelStat newParcelStat = new ParcelStat(parcelId, deliveryStatus, currentUserId, driverId);
		parcelStatRepo.save(newParcelStat);
	}
}
