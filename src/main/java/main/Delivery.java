package main;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		unscheduled, scheduled, delivered, attempted, cancelled;
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
	private int sequence;

	protected Delivery() {}

	public Delivery(LocalDate scheduledDate, LocalDate actualDate, Long customerId, Long parcelId, Status status, Long driverId, int sequence) {
		this.scheduledDate = scheduledDate;
		this.actualDate = actualDate;
		this.customerId = customerId;
		this.parcelId = parcelId;
		this.status = Status.scheduled;
		this.driverId = driverId;
		this.sequence = sequence;
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

	/**
	 * change status and safe changement
	 */
	public void setStatus(Status status) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		new ParcelStat(parcelId, id, status, currentUserName);
		this.status = status;
	}

	public LocalDate getScheduledDate() { return scheduledDate; }

	public void setScheduledDate(LocalDate plannedDate) { this.scheduledDate = plannedDate; }

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
}
