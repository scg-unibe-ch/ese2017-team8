package main;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
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

	private LocalDate scheduledDate;
	private LocalDate actualDate;
	private Long customerId;
	private Long parcelId;
	private Status status;
	private Long driverId;

	protected Delivery() {}

	public Delivery(LocalDate scheduledDate, LocalDate actualDate, Long customerId, Long parcelId, Status status, Long driverId) {
		this.scheduledDate = scheduledDate;
		this.actualDate = actualDate;
		this.customerId = customerId;
		this.parcelId = parcelId;
		this.status = Status.scheduled;
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return String.format("Delivery - Id: %d, ParcelId: %d", id, parcelId);
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

	public Long getDriverId() { return driverId; }

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId){}

	@ModelAttribute("parcelId")
	public Long getParcelId() {
		return parcelId;
	}

	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getScheduledDate() { return scheduledDate; }

	public void setScheduledDate(LocalDate plannedDate) { this.scheduledDate = plannedDate; }
}
