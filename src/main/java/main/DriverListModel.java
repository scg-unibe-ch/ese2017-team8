package main;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class DriverListModel {
	public User driver;
	public List<Long> parcelIds;

	public DriverListModel() { }

	@ModelAttribute("driverListModel")
	public AssignDriverModel getAssignDriverModel() {
		return new AssignDriverModel();
	}

	@ModelAttribute("driverName")
	public String getDriverName() {
		return driver.getUsername();
	}

	@ModelAttribute("driverId")
	public Long getDriverId() { return driver.getId(); }

	@ModelAttribute("driver")
	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		assert(driver.getAuthorities().contains(AuthorityDriver.instance));
		this.driver = driver;
	}

	@ModelAttribute("parcelIds")
	public List<Long> getParcelIds() {
		return parcelIds;
	}

	public void setParcelIds(List<Long> parcelIds) {
		this.parcelIds = parcelIds;
	}
}
