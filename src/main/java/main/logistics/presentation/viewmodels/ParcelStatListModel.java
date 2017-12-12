package main.logistics.presentation.viewmodels;

import main.AuthorityDriver;
import main.common.data.models.Delivery;
import main.common.data.models.Parcel;
import main.common.data.models.ParcelStat;
import main.common.data.models.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.List;

/**
 * provides data for parcelStats
 * was created to show user names instead of ids
 *
 * @see main.logistics.business.LogisticsMainInteractor
 */
public class ParcelStatListModel {
	public User driver;
	public User user;
	public ParcelStat parcelStat;

	public ParcelStatListModel() { }

	@ModelAttribute("parcelStatListModel")
	public ParcelStatListModel getParcelStatListModel() {
		return new ParcelStatListModel();
	}

	@ModelAttribute("driverName")
	public String getDriverName() {
		if (driver != null){
			return driver.getUsername();
		} else {
			return "";
		}

	}

	@ModelAttribute("userName")
	public String getUserName() {
		if (user != null){
			return user.getUsername();
		} else {
			return "";
		}
	}

	@ModelAttribute("parcelStat")
	public ParcelStat ParcelStat() {
		return parcelStat;
	}

	@ModelAttribute("newStatus")
	public Delivery.Status getNewStatus(){
		return parcelStat.getNewStatus();
	}

	@ModelAttribute("changeTime")
	public LocalDateTime getChangeTime(){
		return parcelStat.getChangeTime();
	}

	public void getParcelStat(ParcelStat parcelStat) {
		this.parcelStat = parcelStat;
	}

	public void setParcelStat(ParcelStat parcelStat) {
		this.parcelStat = parcelStat;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
