package main;
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

	protected Delivery() {};

	public Delivery(Date planned, Date actual, Long customerId, Long packageId, Status status) {
		this.plannedDate = plannedDate;
		this.actualDate = actualDate;
		this.customerId = customerId;
		this.packageId = packageId;
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("Delivery - Id: %l", id);
	}

}
