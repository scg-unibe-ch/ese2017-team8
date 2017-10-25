package main;

import org.springframework.web.bind.annotation.ModelAttribute;

public class ParcelListViewModel {
	public Parcel parcel;
	public String driverName;
	public Long parcelId;

	public ParcelListViewModel() {
		this.parcel = new Parcel();
		this.driverName = "";
		this.parcelId = new Long(0);
	}

	@ModelAttribute("parcelList")
	public ParcelListViewModel getParcelListViewModel() {
		return new ParcelListViewModel();
	}

	public Parcel getParcel() {
		return parcel;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Long getParcelId() {
		return parcelId;
	}

	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}
}
