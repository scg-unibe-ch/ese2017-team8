package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * This interface is used to setup the parcel parcelRepo.
 *
 * @author Team8
 * @version 1.0
 */

public interface ParcelRepo extends CrudRepository<Parcel, Long> {

	/**
	 * Gets parcels with the same id.
	 *
	 * @param packageId Long value of the id.
	 * @return List of the parcels.
	 */
	public List<Parcel> getParcelsById(Long packageId);

	/**
	 * Gets all parcels.
	 *
	 * @return List of all parcels.
	 */
	public List<Parcel> findAll();
}