package main;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Delivery 
{
	public enum Status
	{
		newPackage,delivered,attempted; //...
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Date plannedDate;
	private Date actualDate;
	private Customer customer;
	private Package pckg;
	private Status status;
	
	public Delivery(int id, Date plannedDate,Date actualDate, Customer customer, Package pckg, Status status)
	{
		this.id = id;
		this.plannedDate=plannedDate;
		this.actualDate=actualDate;
		this.customer=customer;
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
