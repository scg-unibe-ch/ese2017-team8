package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PackageRepo extends CrudRepository<Package, Long> {

	// Spring Data JPA also allows you to define other query methods by simply declaring their method signature.
    public List<Package> getPackagesById(Long packageId);
}