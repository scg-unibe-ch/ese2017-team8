package main;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

/**
 * This Class is the entity for a Delivery.
 * The entity consists of some ids and dates.
 * This fields are private and can be accessed by invoking the getter and setter methods.
 *
 * @author Team8
 * @version 1.0
 */

@Entity
@Table(name = "deliveries")
public class Delivery
{

	public enum Status {
		unscheduled, scheduled, delivered, attempted;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Calendar plannedDate;
	private Date actualDate;
	private Long customerId;
	private Long packageId;
	private Status status;
	private String driver;

	protected Delivery() {}

	public Delivery(Calendar plannedDate, Date actualDate, Long customerId, Long packageId, Status status, String driver) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 1);
		this.plannedDate = now;
		this.actualDate = actualDate;
		this.customerId = customerId;
		this.packageId = packageId;
		this.status = status;
		this.driver = driver;
	}

	@Override
	public String toString() {
		return String.format("Delivery - Id: %d", id);
	}

	@ModelAttribute("delivery")
	public Delivery getDelivery(){
		return new Delivery();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriver() {
		System.out.println("Nom");
		return "asdf";
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId){}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Calendar getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(Calendar plannedDate) {
		this.plannedDate = plannedDate;
	}
}
