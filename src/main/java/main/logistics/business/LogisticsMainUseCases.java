package main.logistics.business;

import main.logistics.presentation.viewmodels.DriverListModel;
import main.common.data.models.Parcel;
import main.common.data.models.User;

import java.util.List;

public interface LogisticsMainUseCases {
	public List<Parcel> getActiveParcels();
	public List<Parcel> getArchivedParcels();
	public List<DriverListModel> getDriversList();
	public void didSubmitDelivery(User driver, Long parcelId);
	public void didReactivateParcel(Long parcelId, Long deliveryId);
}
