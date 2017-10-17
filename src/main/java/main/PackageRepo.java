package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PackageRepo extends CrudRepository<Package, Long> {
	public List<Package> getPackagesById(Long packageId);
}