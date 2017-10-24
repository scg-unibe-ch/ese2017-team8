package main;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * This interface is used to setup the delivery repository.
 *
 * @author Team8
 * @version 1.0
 */
public interface DeliveryRepo extends CrudRepository<Delivery, Date> {

	/**
	 * Finds all deliveries.
	 * @return List of type Delivery of all deliveries
	 */
	public List<Delivery> findAll();

	/**
	 * Finds all deliveries for the selected driver.
	 * @param driver String which represents the drivers name.
	 * @return List which contains deliveries of the selected driver.
	 */
	public List<Delivery> findByDriver(String driver);

	/**
	 * Gets all deliveries which share the same actual date.
	 * @param actualDate Date when the delivery was registrated.
	 * @return List of deliveries with the same actual date.
	 */
	public List<Delivery> getDeliveriesByActualDate(Date actualDate);

	/**
	 * Gets a List of all deliveries which share the same customer id.
	 * @param customerId Long value of the customer id.
	 * @return	List of all deliveries for the specific id.
	 */
	public List<Delivery> getDeliveriesByCustomerId(Long customerId);
}

