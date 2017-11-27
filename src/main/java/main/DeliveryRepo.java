package main;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;

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


	@Query("SELECT d FROM Delivery d WHERE d.driverId = :driverId AND NOT d.status = 5")
	public List<Delivery> findByDriverId(@Param("driverId") Long driverId);

	@Query("SELECT d FROM Delivery d WHERE NOT d.status = 5")
	public List<Delivery> findAllNotArchived();

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

