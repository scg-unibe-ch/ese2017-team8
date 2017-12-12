package main.logistics.business;

import main.common.data.models.Parcel;

public interface LogisticsNewParcelUseCases {
	public void didSubmitParcel(Parcel parcel);
}
