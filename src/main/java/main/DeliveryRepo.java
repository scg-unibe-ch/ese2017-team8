package main;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface is used to setup the delivery parcelRepo.
 *
 * @author Team8
 * @version 1.0
 */
@Repository
public interface DeliveryRepo extends CrudRepository<Delivery, Long> {

	/**
	 * Finds all deliveries.
	 * @return List of type Delivery of all deliveries
	 */
	public List<Delivery> findAll();

	/**
	 * Finds all deliveries for the selected driver.
	 * @param driverId Long which matches the driver id.
	 * @return List which contains deliveries of the selected driver.
	 */
	public List<Delivery> findByDriverId(Long driverId);

	public Delivery findByParcelId(Long parcelId);

	/**
	 * Gets all deliveries which share the same actual date.
	 * @param actualDate Date when the delivery was registered.
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

