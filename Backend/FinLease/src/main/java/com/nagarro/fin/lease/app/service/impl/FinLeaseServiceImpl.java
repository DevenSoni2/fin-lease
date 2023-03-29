package com.nagarro.fin.lease.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.fin.lease.app.constants.ApplicationConstants;
import com.nagarro.fin.lease.app.dao.entity.Customer;
import com.nagarro.fin.lease.app.dao.entity.LeaseDetail;
import com.nagarro.fin.lease.app.dao.entity.LeaseStatus;
import com.nagarro.fin.lease.app.dao.entity.LeaseStatusCompisteKey;
import com.nagarro.fin.lease.app.dao.entity.Role;
import com.nagarro.fin.lease.app.dao.entity.User;
import com.nagarro.fin.lease.app.dao.repository.CustomerRepository;
import com.nagarro.fin.lease.app.dao.repository.LeaseRepository;
import com.nagarro.fin.lease.app.dao.repository.LeaseStatusRepository;
import com.nagarro.fin.lease.app.dao.repository.UserRepository;
import com.nagarro.fin.lease.app.exceptions.GenericException;
import com.nagarro.fin.lease.app.payload.request.LeaseRequestDTO;
import com.nagarro.fin.lease.app.payload.request.UpdateLeaseStatusReq;
import com.nagarro.fin.lease.app.payload.response.LeaseResponseDTO;
import com.nagarro.fin.lease.app.payload.response.UserDetail;
import com.nagarro.fin.lease.app.security.services.UserDetailsImpl;
import com.nagarro.fin.lease.app.service.FinLeaseService;

/**
 * The Class FinLeaseServiceImpl.
 */
@Service
public class FinLeaseServiceImpl implements FinLeaseService {

	/** The lease repository. */
	@Autowired
	private LeaseRepository leaseRepository;
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The lease status repository. */
	@Autowired
	private LeaseStatusRepository leaseStatusRepository;
	
	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Apply lease.
	 *
	 * @param leaseRequestDTO the lease request DTO
	 * @return the string
	 */
	@Override
	@Transactional
	public String applyLease(@Valid LeaseRequestDTO leaseRequestDTO) {
		Optional<User> opCreatedUser = userRepository.findById(leaseRequestDTO.getCreatedBy());
		Optional<Customer> opProposedUser = customerRepository.findById(leaseRequestDTO.getProposerUserId());
		if (!opCreatedUser.isPresent()) {
			throw new GenericException("User doesn't exist", HttpStatus.NOT_FOUND);
		}
		if(!opProposedUser.isPresent()) {
			throw new GenericException("Proposed User doesn't exist", HttpStatus.NOT_FOUND);
		}
		LeaseDetail leaseDetail = new LeaseDetail();
		leaseDetail.setAssetMake(leaseRequestDTO.getAssetMake());
		leaseDetail.setMonthlyIncome(leaseRequestDTO.getMonthlyIncome());
		leaseDetail.setAssetDeliveryDate(leaseRequestDTO.getAssetDeliveryDate());
		leaseDetail.setAssetModel(leaseRequestDTO.getAssetModel());
		leaseDetail.setAssetPurpose(leaseRequestDTO.getAssetPurpose());
		leaseDetail.setCreatedTime(leaseRequestDTO.getCreatedTime());
		leaseDetail.setCustomerAccountNumber(leaseRequestDTO.getCustomerAccountNumber());
		leaseDetail.setCustomerNumber(leaseRequestDTO.getCustomerNumber());
		leaseDetail.setCustomerName(leaseRequestDTO.getCustomerName());
		leaseDetail.setLeasePeriodInMonth(leaseRequestDTO.getLeasePeriodInMonth());
		leaseDetail.setLeaseType(leaseRequestDTO.getLeaseType());
		leaseDetail.setPhoneNumber(leaseRequestDTO.getPhoneNumber());
		leaseDetail.setStatus(ApplicationConstants.APPLIED);
		leaseDetail.setRequiredAmount(leaseRequestDTO.getRequiredAmount());
		leaseDetail.setCreatedBy(opCreatedUser.get());
		leaseDetail.setProposerUserId(opProposedUser.get());
		leaseDetail.setOtherIncome(leaseRequestDTO.getOtherIncome());
		leaseDetail.setTotalIncome(leaseRequestDTO.getTotalIncome());
		leaseDetail.setCommentRecommendation(leaseRequestDTO.getCommentRecommendation());
		leaseDetail.setTotalCommitmentMCB(leaseRequestDTO.getTotalCommitmentMCB());
		leaseDetail.setTotalCommitmentOther(leaseRequestDTO.getTotalCommitmentOther());
		leaseDetail.setCreditTurnover(leaseRequestDTO.getCreditTurnover());
		leaseDetail.setDebitTurnover(leaseRequestDTO.getDebitTurnover());
		leaseDetail.setSetUpParameter(leaseRequestDTO.getSetUpParameter());

		leaseRepository.save(leaseDetail);

		return leaseDetail.getReferenceId();
	}

	/**
	 * Fetch lease info.
	 *
	 * @param referenceId the reference id
	 * @return the lease response DTO
	 */
	@Override
	public LeaseResponseDTO fetchLeaseInfo(String referenceId) {
		Optional<LeaseDetail> opLeaseDetail = leaseRepository.findById(referenceId);
		if (!opLeaseDetail.isPresent()) {
			throw new GenericException("Lease detail doesn't exist for given reference Id: " + referenceId,
					HttpStatus.NOT_FOUND);
		}
		LeaseDetail det = opLeaseDetail.get();
		LeaseResponseDTO response = tranformEntityToDTO(det);
		return response;
	}

	/**
	 * Tranform entity to DTO.
	 *
	 * @param det the det
	 * @return the lease response DTO
	 */
	private LeaseResponseDTO tranformEntityToDTO(LeaseDetail det) {
		LeaseResponseDTO response = new LeaseResponseDTO();
		response.setMonthlyIncome(det.getMonthlyIncome());
		response.setAssetDeliveryDate(det.getAssetDeliveryDate());
		response.setAssetMake(det.getAssetMake());
		response.setAssetModel(det.getAssetModel());
		response.setAssetPurpose(det.getAssetPurpose());
		response.setCreatedBy(det.getCreatedBy().getId());
		response.setBuisnessUnit(det.getProposerUserId().getBuisnessUnit());
		response.setCreatedTime(det.getCreatedTime());
		response.setCustomerAccountNumber(det.getCustomerAccountNumber());
		response.setCustomerNumber(det.getCustomerNumber());
		response.setLeasePeriodInMonth(det.getLeasePeriodInMonth());
		response.setLeaseType(det.getLeaseType());
		response.setPhoneNumber(det.getPhoneNumber());
		response.setProposerUserId(det.getProposerUserId().getId());
		response.setPropserUserName(det.getProposerUserId().getUserName());
		response.setRequiredAmount(det.getRequiredAmount());
		response.setReferenceId(det.getReferenceId());
		response.setStatus(det.getStatus());
		response.setOtherIncome(det.getOtherIncome());
		response.setTotalIncome(det.getTotalIncome());
		response.setCommentRecommendation(det.getCommentRecommendation());
		response.setTotalCommitmentMCB(det.getTotalCommitmentMCB());
		response.setTotalCommitmentOther(det.getTotalCommitmentOther());
		response.setCreditTurnover(det.getCreditTurnover());
		response.setDebitTurnover(det.getDebitTurnover());
		response.setSetUpParameter(det.getSetUpParameter());
		response.setCustomerName(det.getCustomerName());
		return response;
	}

	/**
	 * Update lease status.
	 *
	 * @param updateLeaseReq the update lease req
	 */
	@Override
	@Transactional
	public void updateLeaseStatus(UpdateLeaseStatusReq updateLeaseReq) {
		Optional<LeaseDetail> opLeaseDetail = leaseRepository.findById(updateLeaseReq.getLeaseReferenceId());
		if (!opLeaseDetail.isPresent()) {
			throw new GenericException(
					"Lease detail doesn't exist for given reference Id: " + updateLeaseReq.getLeaseReferenceId(),
					HttpStatus.NOT_FOUND);
		}
		Optional<User> opUpdatedByser = userRepository.findById(updateLeaseReq.getUpdatedBy());
		if (!opUpdatedByser.isPresent()) {
			throw new GenericException("User doesn't exist", HttpStatus.NOT_FOUND);
		}
		LeaseDetail leaseDetail = opLeaseDetail.get();
		leaseDetail.setStatus(updateLeaseReq.getStatus());
		LeaseStatus leaseStatus = new LeaseStatus();
		LeaseStatusCompisteKey compositeKey = new LeaseStatusCompisteKey();
		compositeKey.setReferenceId(leaseDetail);
		compositeKey.setUpdatedBy(opUpdatedByser.get());
		leaseStatus.setLeaseStatusCompisteId(compositeKey);
		leaseStatus.setApprovalDate(updateLeaseReq.getApprovalDate());
		leaseStatus.setAccountOfficerComment(updateLeaseReq.getAccountOfficerComment());
		leaseStatus.setComment(updateLeaseReq.getComment());
		leaseStatus.setInterestRate(updateLeaseReq.getInterestRate());
		leaseStatus.setMonthlyRepayment(updateLeaseReq.getMonthlyRepayment());
		leaseStatus.setResidualValue(updateLeaseReq.getResidualValue());
		leaseStatus.setApprovalAmount(updateLeaseReq.getApprovalAmount());
		leaseStatusRepository.save(leaseStatus);

	}

	/**
	 * Fetch user detail.
	 *
	 * @param userId the user id
	 * @return the user detail
	 */
	@Override
	public UserDetail fetchUserDetail(String userId) {
		Optional<User> opUser = userRepository.findById(userId);
		Optional<Role> opRole = opUser.get().getRoles().stream().findFirst();
		if (!opUser.isPresent() || !opRole.isPresent()) {
			throw new GenericException("User doesn't exist", HttpStatus.NOT_FOUND);
		}
		UserDetail userDetail = new UserDetail();
		userDetail.setBuisnessUnit(opUser.get().getBuisnessUnit());
		userDetail.setUserId(userId);
		userDetail.setUserName(opUser.get().getUsername());
		userDetail.setRoleId(opRole.get().getId());
		return userDetail;
	}

	/**
	 * Fetch lease list.
	 *
	 * @param status the status
	 * @return the list
	 */
	@Override
	public List<LeaseResponseDTO> fetchLeaseList(String status) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<LeaseResponseDTO> list = new ArrayList<>();
		Integer roleId = userDetails.getRoleId();
		if (roleId.equals(ApplicationConstants.ADMIN)) {
			populateAdminList(status, list);
		} else {
			List<LeaseDetail> leaseDetails = leaseRepository.findOwnCreatedLease(userDetails.getId());
			leaseDetails.stream().forEach(el -> {
				LeaseResponseDTO response = tranformEntityToDTO(el);
				list.add(response);
			});
		}
		return list;
	}

	/**
	 * Populate admin list.
	 *
	 * @param status the status
	 * @param list the list
	 */
	private void populateAdminList(String status, List<LeaseResponseDTO> list) {
		if (status.equals(ApplicationConstants.OPEN)) {
			List<LeaseDetail> leaseDetails = leaseRepository.findByLeaseStatus();
			leaseDetails.stream().forEach(el -> {
				LeaseResponseDTO response = tranformEntityToDTO(el);
				list.add(response);
			});
		} else if (status.equals(ApplicationConstants.CLOSED)) {
			List<LeaseStatus> leaseStatusList = leaseStatusRepository.findClosedLeaseApplications();
			leaseStatusList.stream().forEach(el -> {
				LeaseResponseDTO response = tranformEntityToDTO(el.getLeaseStatusCompisteId().getReferenceId());
				response.setApprovalComment(el.getComment());
				response.setApprovalDate(el.getApprovalDate());
				response.setApprovalUserId(el.getLeaseStatusCompisteId().getUpdatedBy().getId());
				list.add(response);
			});
		} else {
			throw new GenericException("Please provide valid status string", HttpStatus.BAD_REQUEST);
		}
	}

}
