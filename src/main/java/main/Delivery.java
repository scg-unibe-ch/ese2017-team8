package main;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery
{
	public enum Status {
		newPackage, delivered, attempted; //...
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Date plannedDate;
	private Date actualDate;
	private Long customerId;
	private Long pckg;
	private Status status;

	protected Delivery() {};

	public Delivery(Date plannedDate,Date actualDate, Long customerId, Long pckg, Status status)
	{
		this.plannedDate=plannedDate;
		this.actualDate=actualDate;
		this.customerId =customerId;
		this.pckg=pckg;
		this.status=status;
	}

	@Override
	public String toString()
	{
		String tmp= "Delivery id= " + id;
		return tmp;
	}

}
