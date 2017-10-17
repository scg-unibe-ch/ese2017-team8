package main;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepo extends CrudRepository<Delivery, Date> 
{
	  public List<Delivery> getDeliveriesByDate(Date actualDate);
	  public List<Delivery> getDeliveriesByClient(Customer customer);
}

	
