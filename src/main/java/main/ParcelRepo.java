package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ParcelRepo extends CrudRepository<Parcel, Long> {
	public List<Parcel> getParcelsById(Long packageId);
	public List<Parcel> findAll();
}