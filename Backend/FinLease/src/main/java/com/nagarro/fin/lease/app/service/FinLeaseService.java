package com.nagarro.fin.lease.app.service;

import java.util.List;

import javax.validation.Valid;

import com.nagarro.fin.lease.app.payload.request.LeaseRequestDTO;
import com.nagarro.fin.lease.app.payload.request.UpdateLeaseStatusReq;
import com.nagarro.fin.lease.app.payload.response.LeaseResponseDTO;
import com.nagarro.fin.lease.app.payload.response.UserDetail;

/**
 * The Interface FinLeaseService.
 */
public interface FinLeaseService {

	/**
	 * Apply lease.
	 *
	 * @param leaseRequestDTO the lease request DTO
	 * @return the string
	 */
	String applyLease(@Valid LeaseRequestDTO leaseRequestDTO);

	/**
	 * Fetch lease info.
	 *
	 * @param referenceId the reference id
	 * @return the lease response DTO
	 */
	LeaseResponseDTO fetchLeaseInfo(String referenceId);

	/**
	 * Update lease status.
	 *
	 * @param updateLeaseReq the update lease req
	 */
	void updateLeaseStatus(UpdateLeaseStatusReq updateLeaseReq);

	/**
	 * Fetch user detail.
	 *
	 * @param userId the user id
	 * @return the user detail
	 */
	UserDetail fetchUserDetail(String userId);

	/**
	 * Fetch lease list.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<LeaseResponseDTO> fetchLeaseList(String status);
}
