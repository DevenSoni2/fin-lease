package com.nagarro.fin.lease.app.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.fin.lease.app.dao.entity.LeaseDetail;

/**
 * The Interface LeaseRepository.
 */
public interface LeaseRepository extends JpaRepository<LeaseDetail, String> {
	
	/**
	 * Find by reference id.
	 *
	 * @param referenceId the reference id
	 * @return the optional
	 */
	Optional<LeaseDetail> findByReferenceId(String referenceId);

	/**
	 * Find by lease status.
	 *
	 * @return the list
	 */
	@Query(value = "SELECT l FROM LeaseDetail l WHERE l.status = 'Applied' ORDER BY l.createdTime DESC")
	List<LeaseDetail> findByLeaseStatus();
	
	/**
	 * Find own created lease.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Query(value = "SELECT l FROM LeaseDetail l WHERE l.createdBy.id = ?1 ORDER BY l.createdTime DESC")
	List<LeaseDetail> findOwnCreatedLease(String userId);

}
