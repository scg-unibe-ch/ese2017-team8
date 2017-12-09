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

	@Query("SELECT COUNT(newStatus) FROM ParcelStat WHERE newStatus = 3 AND driver = :driverName")
	public Long countAttemptedParcelsForDriver(@Param("driverName") String driverName);

	@Query("SELECT COUNT(newStatus) FROM ParcelStat WHERE newStatus = 2 AND driver = :driverName")
	public Long countDeliveredParcelsForDriver(@Param("driverName") String driverName);
}

