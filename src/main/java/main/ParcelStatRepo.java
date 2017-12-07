package main;

import org.springframework.data.repository.CrudRepository;
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
}

