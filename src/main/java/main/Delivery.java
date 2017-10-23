package main;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

import javax.persistence.*;

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

	private Date plannedDate;
	private Date actualDate;
	private Long customerId;
	private Long packageId;
	private Status status;
	private String driver;

	protected Delivery() {}

	public Delivery(Date planned, Date actual, Long customerId, Long packageId, Status status, String driver) {
		this.plannedDate = plannedDate;
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
		return driver;
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
}
