package main.common.business.createdelivery;

import main.common.data.models.User;

import java.time.LocalDate;

public interface CreateDeliveryUseCases {
	public Long createScheduledDelivery(User driver, Long parcelId);
	public Long createScheduledDeliveryWithDate(User driver, Long parcelId, LocalDate date);
}
