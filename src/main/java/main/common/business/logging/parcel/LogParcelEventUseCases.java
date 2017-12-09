package main.common.business.logging.parcel;

import main.common.data.models.Delivery;
import main.common.data.models.Parcel;

public interface LogParcelEventUseCases {
	public void logParcelEvent(Long parcelId, Delivery.Status newStatus, Long currentUserId, Long driverId);
}
