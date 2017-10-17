package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, String> {

	// Spring Data JPA also allows you to define other query methods by simply declaring their method signature.
    public List<Customer> findByLastName(String lastName);
}