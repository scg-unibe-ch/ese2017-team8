package main;

import org.springframework.web.bind.annotation.ModelAttribute;

public class AssignDriverModel {
	public User driver;
	public Long parcelId;

	public AssignDriverModel() { }

	@ModelAttribute("assignDriverModel")
	public AssignDriverModel getAssignDriverModel() {
		return new AssignDriverModel();
	}

	@ModelAttribute("driver")
	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		assert(driver.getAuthorities().contains(AuthorityDriver.instance));
		this.driver = driver;
	}

	@ModelAttribute("parcelId")
	public Long getParcelId() {
		return parcelId;
	}

	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}
}
