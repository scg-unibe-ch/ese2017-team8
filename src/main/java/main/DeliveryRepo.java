package main;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepo extends CrudRepository<Delivery, Date> {
	public List<Delivery> findAll();
	public List<Delivery> findByDriver(String driver);
	public List<Delivery> getDeliveriesByActualDate(Date actualDate);
	public List<Delivery> getDeliveriesByCustomerId(Long customerId);
}

