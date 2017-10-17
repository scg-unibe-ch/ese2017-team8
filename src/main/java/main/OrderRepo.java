package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
    public List<Order> findByCustomerId(Long customerID);
}