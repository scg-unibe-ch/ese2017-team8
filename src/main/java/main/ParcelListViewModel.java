package main;

import org.springframework.web.bind.annotation.ModelAttribute;

public class ParcelListViewModel {
	public Parcel parcel;
	public String driverName;

	public ParcelListViewModel() {
		this.parcel = new Parcel();
		this.driverName = "";
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
}
