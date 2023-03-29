package com.nagarro.fin.lease.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nagarro.fin.lease.app.constants.ApplicationConstants;
import com.nagarro.fin.lease.app.dao.entity.Customer;
import com.nagarro.fin.lease.app.dao.entity.ERole;
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
import com.nagarro.fin.lease.app.security.services.UserDetailsImpl;

/**
 * The Class FinLeaseServiceImplTest.
 */
@ExtendWith(MockitoExtension.class)
class FinLeaseServiceImplTest {

	/** The service impl. */
	@InjectMocks
	private FinLeaseServiceImpl serviceImpl;

	/** The lease repository. */
	@Mock
	private LeaseRepository leaseRepository;

	/** The lease status repository. */
	@Mock
	private LeaseStatusRepository leaseStatusRepository;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	

	/**
	 * Test apply lease.
	 */
	@Test
	void testApplyLease() {
		User user = new User();
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Customer()));
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
		Mockito.when(leaseRepository.save(Mockito.any())).thenReturn(null);
		LeaseRequestDTO req = new LeaseRequestDTO();
		req.setCreatedBy("CUS_00001");
		req.setProposerUserId("CUS_00001");
		serviceImpl.applyLease(req);
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyString());
	}

	/**
	 * Test apply lease user not found 1.
	 */
	@Test
	void testApplyLeaseUserNotFound1() {
		LeaseRequestDTO req = new LeaseRequestDTO();
		req.setCreatedBy("CUS_00001");
		req.setProposerUserId("CUS_00001");

		assertThrows(GenericException.class, () -> {
			serviceImpl.applyLease(req);
			;
		});
	}

	/**
	 * Test apply lease user not found 2.
	 */
	@Test
	void testApplyLeaseUserNotFound2() {
		User user = new User();
		Mockito.when(userRepository.findById("CUS_00001")).thenReturn(Optional.of(user));
		LeaseRequestDTO req = new LeaseRequestDTO();
		req.setCreatedBy("CUS_00001");
		req.setProposerUserId("CUS_00002");

		assertThrows(GenericException.class, () -> {
			serviceImpl.applyLease(req);
			;
		});
	}

	/**
	 * Test fetch lease info not found.
	 */
	@Test
	void testFetchLeaseInfoNotFound() {
		assertThrows(GenericException.class, () -> {
			serviceImpl.fetchLeaseInfo("FRA2023032000001");
		});
	}

	/**
	 * Test fetch lease info found.
	 */
	@Test
	void testFetchLeaseInfoFound() {
		LeaseDetail leaseDetail = new LeaseDetail();
		leaseDetail.setCreatedBy(new User());
		leaseDetail.setProposerUserId(new Customer());
		Mockito.when(leaseRepository.findById(Mockito.anyString())).thenReturn(Optional.of(leaseDetail));
		serviceImpl.fetchLeaseInfo("FRA2023032000001");
		Mockito.verify(leaseRepository, Mockito.times(1)).findById(Mockito.anyString());
	}

	/**
	 * Test update lease status.
	 */
	@Test
	void testUpdateLeaseStatus() {
		LeaseDetail leaseDetail = new LeaseDetail();
		leaseDetail.setCreatedBy(new User());
		leaseDetail.setProposerUserId(new Customer());
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new User()));
		Mockito.when(leaseRepository.findById(Mockito.anyString())).thenReturn(Optional.of(leaseDetail));
		UpdateLeaseStatusReq req = new UpdateLeaseStatusReq();
		req.setLeaseReferenceId("FRA2023032000001");
		req.setUpdatedBy("CUS_00001");
		serviceImpl.updateLeaseStatus(req);
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyString());
	}

	/**
	 * Test update lease status when lease not found.
	 */
	@Test
	void testUpdateLeaseStatusWhenLeaseNotFound() {
		UpdateLeaseStatusReq req = new UpdateLeaseStatusReq();
		req.setLeaseReferenceId("FRA2023032000001");
		req.setUpdatedBy("CUS_00001");
		assertThrows(GenericException.class, () -> {
			serviceImpl.updateLeaseStatus(req);
		});

	}

	/**
	 * Test update lease status when user not found.
	 */
	@Test
	void testUpdateLeaseStatusWhenUserNotFound() {
		LeaseDetail leaseDetail = new LeaseDetail();
		leaseDetail.setCreatedBy(new User());
		leaseDetail.setProposerUserId(new Customer());
		Mockito.when(leaseRepository.findById(Mockito.anyString())).thenReturn(Optional.of(leaseDetail));
		UpdateLeaseStatusReq req = new UpdateLeaseStatusReq();
		req.setLeaseReferenceId("FRA2023032000001");
		req.setUpdatedBy("CUS_00001");
		assertThrows(GenericException.class, () -> {
			serviceImpl.updateLeaseStatus(req);
		});
	}

	/**
	 * Test fetch user detail not found.
	 */
	@Test
	void testFetchUserDetailNotFound() {
		Set<Role> roles = new HashSet<>();
		User user = new User();
		user.setRoles(roles);
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
		assertThrows(GenericException.class, () -> {
			serviceImpl.fetchUserDetail("CUS_0001");
		});
	}

	/**
	 * Test fetch user detail.
	 */
	@Test
	void testFetchUserDetail() {
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(1);
		roles.add(role);
		User user = new User();
		user.setRoles(roles);
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
		serviceImpl.fetchUserDetail("CUS_0001");
		Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.anyString());
	}

	/**
	 * Test fetch lease list admin open.
	 */
	@Test
	void testFetchLeaseListAdminOpen() {
		Authentication authentication = Mockito.mock(Authentication.class);
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(1);
		role.setName(ERole.ADMIN);
		roles.add(role);
		User user = new User();
		user.setRoles(roles);
		Mockito.when(authentication.getPrincipal()).thenReturn(UserDetailsImpl.build(user));
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		List<LeaseDetail> leaseDetails = new ArrayList<>();
		LeaseDetail leaseDetail = new LeaseDetail();
		leaseDetail.setCreatedBy(new User());
		leaseDetail.setProposerUserId(new Customer());
		leaseDetails.add(leaseDetail);
		Mockito.when(leaseRepository.findByLeaseStatus()).thenReturn(leaseDetails);
		serviceImpl.fetchLeaseList(ApplicationConstants.OPEN);
	}

	/**
	 * Test fetch lease list admin closed.
	 */
	@Test
	void testFetchLeaseListAdminClosed() {
		Authentication authentication = Mockito.mock(Authentication.class);
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(1);
		role.setName(ERole.ADMIN);
		roles.add(role);
		User user = new User();
		user.setRoles(roles);
		Mockito.when(authentication.getPrincipal()).thenReturn(UserDetailsImpl.build(user));
		// Mockito.whens() for your authorization object
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		List<LeaseStatus> leaseDetails = new ArrayList<>();
		LeaseDetail leaseDetail = new LeaseDetail();
		leaseDetail.setCreatedBy(new User());
		leaseDetail.setProposerUserId(new Customer());
		LeaseStatus leaseStatus = new LeaseStatus();
		LeaseStatusCompisteKey compisteKey = new LeaseStatusCompisteKey();
		compisteKey.setReferenceId(leaseDetail);
		compisteKey.setUpdatedBy(new User());
		leaseStatus.setLeaseStatusCompisteId(compisteKey);
		leaseDetails.add(leaseStatus);
		Mockito.when(leaseStatusRepository.findClosedLeaseApplications()).thenReturn(leaseDetails);
		serviceImpl.fetchLeaseList(ApplicationConstants.CLOSED);
	}

	/**
	 * Test fetch lease list admin invalid.
	 */
	@Test
	void testFetchLeaseListAdminInvalid() {
		Authentication authentication = Mockito.mock(Authentication.class);
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(1);
		role.setName(ERole.ADMIN);
		roles.add(role);
		User user = new User();
		user.setRoles(roles);
		Mockito.when(authentication.getPrincipal()).thenReturn(UserDetailsImpl.build(user));
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		assertThrows(GenericException.class, () -> {
			serviceImpl.fetchLeaseList(ApplicationConstants.FILE_TYPE);
		});
	}

	/**
	 * Test fetch lease list user open.
	 */
	@Test
	void testFetchLeaseListUserOpen() {
		Authentication authentication = Mockito.mock(Authentication.class);
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(2);
		role.setName(ERole.EMPLOYEE);
		roles.add(role);
		User user = new User();
		user.setRoles(roles);
		user.setId("CUS_00001");
		Mockito.when(authentication.getPrincipal()).thenReturn(UserDetailsImpl.build(user));
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		List<LeaseDetail> leaseDetails = new ArrayList<>();
		LeaseDetail leaseDetail = new LeaseDetail();
		leaseDetail.setCreatedBy(new User());
		leaseDetail.setProposerUserId(new Customer());
		leaseDetails.add(leaseDetail);
		Mockito.when(leaseRepository.findOwnCreatedLease(Mockito.anyString())).thenReturn(leaseDetails);
		serviceImpl.fetchLeaseList(ApplicationConstants.OPEN);
	}
}
