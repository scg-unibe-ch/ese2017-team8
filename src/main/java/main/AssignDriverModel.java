package main;

import org.springframework.web.bind.annotation.ModelAttribute;

public class AssignDriverModel {
	public String driverName;
	public Long parcelId;

	public AssignDriverModel() {
		this.driverName = "";
		this.parcelId = new Long(0);
	}

	@ModelAttribute("assignDriverModel")
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

	@ModelAttribute("parcelId")
	public Long getParcelId() {
		return parcelId;
	}

	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}
}
