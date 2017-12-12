package main.common.data.repositories;

import java.util.List;

import main.common.data.models.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface is used to setup the customer repository.
 *
 * @author Team8
 * @version 1.0
 */
public interface CustomerRepo extends CrudRepository<Customer, String> {

	/**
	 * Finds customers by last name.
	 * @param lastName String of the searched name.
	 * @return List of customers with the given name.
	 */
	public List<Customer> findByLastName(String lastName);
}