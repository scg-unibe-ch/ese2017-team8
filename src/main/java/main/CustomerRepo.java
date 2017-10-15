package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    public List<Customer> findByLastName(String lastName);
}