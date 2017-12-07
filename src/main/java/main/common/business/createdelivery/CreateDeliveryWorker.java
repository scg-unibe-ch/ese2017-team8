package main.common.business.createdelivery;

import main.common.data.repositories.DeliveryRepo;
import main.common.data.repositories.ParcelRepo;
import main.common.data.models.Delivery;
import main.common.data.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateDeliveryWorker implements CreateDeliveryUseCases {
	@Autowired
	DeliveryRepo deliveryRepo;

	@Autowired
	ParcelRepo parcelRepo;

	public Long createScheduledDelivery(User driver, Long parcelId) {
		// Schedule parcel for next day
		LocalDate scheduledDate = LocalDate.now().plusDays(1);
		return createScheduledDeliveryWithDate(driver, parcelId, scheduledDate);
	}

	public Long createScheduledDeliveryWithDate(User driver, Long parcelId, LocalDate date) {
		assert parcelId != null;
		// If delivery already exists we simply update its values
		Delivery delivery = deliveryRepo.findByParcelId(parcelId);

		if (delivery == null) {
			delivery = new Delivery();
		}

		delivery.setDriverId(driver.getId());
		delivery.setParcelId(parcelId);
		delivery.setScheduledDate(date);
		delivery.setStatus(Delivery.Status.scheduled);

		Delivery saved = deliveryRepo.save(delivery);
		System.out.println(deliveryRepo.findAll().size());
		return saved.getId();
	}
}
