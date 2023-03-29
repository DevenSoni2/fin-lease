package com.nagarro.fin.lease.app.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.fin.lease.app.constants.ApplicationConstants;
import com.nagarro.fin.lease.app.payload.request.LeaseRequestDTO;
import com.nagarro.fin.lease.app.payload.request.UpdateLeaseStatusReq;
import com.nagarro.fin.lease.app.payload.response.LeaseResponseDTO;
import com.nagarro.fin.lease.app.payload.response.UserDetail;
import com.nagarro.fin.lease.app.service.FinLeaseService;

/**
 * The Class FinanceLeaseApplicationControllerTest.
 */
@ExtendWith(MockitoExtension.class)
public class FinanceLeaseApplicationControllerTest {

	/** The controller. */
	@InjectMocks
	private FinanceLeaseApplicationController controller;

	/** The service. */
	@Mock
	private FinLeaseService service;

	/**
	 * Test apply lease.
	 */
	@Test
	void testApplyLease() {
		Mockito.when(service.applyLease(Mockito.any())).thenReturn("FLA202312200001");
		controller.applyLease(new LeaseRequestDTO());
		Mockito.verify(service, Mockito.times(1)).applyLease(Mockito.any());
	}

	/**
	 * Test fetch lease info.
	 */
	@Test
	void testFetchLeaseInfo() {
		Mockito.when(service.fetchLeaseInfo(Mockito.anyString())).thenReturn(new LeaseResponseDTO());
		controller.fetchLeaseInfo("FLA202312200001");
		Mockito.verify(service, Mockito.times(1)).fetchLeaseInfo(Mockito.anyString());
	}

	/**
	 * Test update lease status.
	 */
	@Test
	void testUpdateLeaseStatus() {
		Mockito.doNothing().when(service).updateLeaseStatus(Mockito.any());
		controller.updateLeaseStatus(new UpdateLeaseStatusReq());
		Mockito.verify(service, Mockito.times(1)).updateLeaseStatus(Mockito.any());
	}

	/**
	 * Test fetch user detail.
	 */
	@Test
	void testFetchUserDetail() {
		Mockito.when(service.fetchUserDetail(Mockito.anyString())).thenReturn(new UserDetail());
		controller.fetchUserDetail("CUS_00001");
		Mockito.verify(service, Mockito.times(1)).fetchUserDetail(Mockito.any());
	}

	/**
	 * Testfetch all lease.
	 */
	@Test
	void testfetchAllLease() {
		Mockito.when(service.fetchLeaseList(Mockito.anyString())).thenReturn(new ArrayList<>());
		controller.fetchAllLease(ApplicationConstants.OPEN);
		Mockito.verify(service, Mockito.times(1)).fetchLeaseList(Mockito.any());
	}

	/**
	 * Test download lease list open.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testDownloadLeaseListOpen() throws IOException {
		HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
		HttpServletRequest re = Mockito.mock(HttpServletRequest.class);
		List<LeaseResponseDTO> leaseResponseDTOs = new ArrayList<>();
		LeaseResponseDTO el = new LeaseResponseDTO();
		el.setReferenceId("FLA202312200001");
		el.setCreatedTime(LocalDateTime.now());
		leaseResponseDTOs.add(el);
		Mockito.when(service.fetchLeaseList(Mockito.anyString())).thenReturn(leaseResponseDTOs);

		assertThrows(OpenXML4JRuntimeException.class, () -> {
			controller.downloadLeaseList(re, res, ApplicationConstants.OPEN);
		});
	}

	/**
	 * Test download lease list closed.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void testDownloadLeaseListClosed() throws IOException {
		HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
		HttpServletRequest re = Mockito.mock(HttpServletRequest.class);
		List<LeaseResponseDTO> leaseResponseDTOs = new ArrayList<>();
		LeaseResponseDTO el = new LeaseResponseDTO();
		el.setReferenceId("FLA202312200001");
		el.setCreatedTime(LocalDateTime.now());
		el.setApprovalDate(LocalDateTime.now());
		leaseResponseDTOs.add(el);
		Mockito.when(service.fetchLeaseList(Mockito.anyString())).thenReturn(leaseResponseDTOs);

		assertThrows(OpenXML4JRuntimeException.class, () -> {
			controller.downloadLeaseList(re, res, ApplicationConstants.CLOSED);
		});
	}
}
