package main.common.data.repositories;

import main.common.data.models.ParcelStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is used to setup the delivery parcelStatRepo.
 *
 * @author Team8
 * @version 1.0
 */
@Repository
public interface ParcelStatRepo extends CrudRepository<ParcelStat, Long> {
	public List<ParcelStat> findAll();
	public List<ParcelStat> findByParcelId(Long parcelId);

	@Query("SELECT COUNT(newStatus) FROM ParcelStat WHERE newStatus = 1 AND driverId = :driverId")
	public Long countScheduledParcelsForDriver(@Param("driverId") Long driverId);

	@Query("SELECT COUNT(newStatus) FROM ParcelStat WHERE newStatus = 2 AND driverId = :driverId")
	public Long countDeliveredParcelsForDriver(@Param("driverId") Long driverId);

	@Query("SELECT COUNT(newStatus) FROM ParcelStat WHERE newStatus = 2 AND parcelId = :parcelId")
	public Long countAttemptedForParcel(@Param("parcelId") Long parcelId);

	@Query("SELECT COUNT(newStatus) FROM ParcelStat WHERE newStatus = 3 AND driverId = :driverId")
	public Long countAttemptedParcelsForDriver(@Param("driverId") Long driverId);
}

