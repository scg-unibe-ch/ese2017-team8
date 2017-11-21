package main;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This interface is used to setup the parcel parcelRepo.
 *
 * @author Team8
 * @version 1.0
 */

public interface ParcelRepo extends CrudRepository<Parcel, Long> {

	/**
	 * @param packageId
	 * @return Parcel with certain ID
	 */
	public Parcel getParcelById(Long packageId);

	/**
	 * Gets all parcels.
	 *
	 * @return List of all parcels.
	 */
	public List<Parcel> findAll();

	@Transactional
	Long deleteById(Long packageId);

}