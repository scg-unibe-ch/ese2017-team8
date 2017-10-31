package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class CreateDeliveryInteractor {
	@Autowired
	DeliveryRepo deliveryRepo;

	@Autowired
	ParcelRepo parcelRepo;

	public Long createScheduledDelivery(String driver, Long parcelId) {
		// Schedule parcel for next day
		LocalDate scheduledDate = LocalDate.now().plusDays(1);
		return createScheduledDeliveryWithDate(driver, parcelId, scheduledDate);
	}

	public Long createScheduledDeliveryWithDate(String driver, Long parcelId, LocalDate date) {
		Delivery delivery = new Delivery();
		delivery.setDriver(driver);
		delivery.setParcelId(parcelId);
		delivery.setScheduledDate(date);

		Delivery saved = deliveryRepo.save(delivery);
		return saved.getId();
	}
}
