package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PackageRepo extends CrudRepository<Package, Integer> {

	// Spring Data JPA also allows you to define other query methods by simply declaring their method signature.
    public List<Package> getPackagesById(int packageId);
}