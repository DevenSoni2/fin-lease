package com.nagarro.fin.lease.app.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.fin.lease.app.dao.entity.LeaseStatus;
import com.nagarro.fin.lease.app.dao.entity.LeaseStatusCompisteKey;

/**
 * The Interface LeaseStatusRepository.
 */
public interface LeaseStatusRepository extends JpaRepository<LeaseStatus, LeaseStatusCompisteKey> {

	/**
	 * Find closed lease applications.
	 *
	 * @return the list
	 */
	@Query(value = "SELECT s FROM LeaseDetail l JOIN LeaseStatus s ON l.referenceId = s.leaseStatusCompisteId.referenceId WHERE l.status !='Applied'")
	public List<LeaseStatus> findClosedLeaseApplications();
}
