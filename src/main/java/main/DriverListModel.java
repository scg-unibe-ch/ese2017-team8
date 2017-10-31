package main;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class DriverListModel {
	public String driverName;
	public List<Long> parcelIds;

	public DriverListModel() {
		this.driverName = "";
		this.parcelIds = new ArrayList<Long>();
	}

	@ModelAttribute("driverListModel")
	public AssignDriverModel getAssignDriverModel() {
		return new AssignDriverModel();
	}

	@ModelAttribute("driverName")
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@ModelAttribute("parcelIds")
	public List<Long> getParcelIds() {
		return parcelIds;
	}

	public void setParcelIds(List<Long> parcelIds) {
		this.parcelIds = parcelIds;
	}
}
