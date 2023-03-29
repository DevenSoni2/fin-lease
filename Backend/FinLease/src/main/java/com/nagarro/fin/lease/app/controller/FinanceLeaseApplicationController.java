package com.nagarro.fin.lease.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.fin.lease.app.constants.ApplicationConstants;
import com.nagarro.fin.lease.app.payload.request.LeaseRequestDTO;
import com.nagarro.fin.lease.app.payload.request.UpdateLeaseStatusReq;
import com.nagarro.fin.lease.app.payload.response.CustomResponse;
import com.nagarro.fin.lease.app.payload.response.LeaseResponseDTO;
import com.nagarro.fin.lease.app.service.FinLeaseService;
import com.nagarro.fin.lease.app.service.impl.LeaseExporterClass;

/**
 * The Class FinanceLeaseApplicationController.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lease")
public class FinanceLeaseApplicationController {

	/** The fin lease service. */
	@Autowired
	private FinLeaseService finLeaseService;

	/**
	 * Apply lease.
	 *
	 * @param leaseRequestDTO the lease request DTO
	 * @return the response entity
	 */
	@PostMapping("/apply")
	@PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
	public ResponseEntity<?> applyLease(@Valid @RequestBody LeaseRequestDTO leaseRequestDTO) {
		String referenceId = finLeaseService.applyLease(leaseRequestDTO);
		return new ResponseEntity<>(new CustomResponse("Lease applied successfully", referenceId), HttpStatus.CREATED);
	}

	/**
	 * Fetch lease info.
	 *
	 * @param referenceId the reference id
	 * @return the response entity
	 */
	@GetMapping("/info/{referenceId}")
	@PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
	public ResponseEntity<?> fetchLeaseInfo(@PathVariable String referenceId) {
		return new ResponseEntity<>(finLeaseService.fetchLeaseInfo(referenceId), HttpStatus.OK);
	}

	/**
	 * Update lease status.
	 *
	 * @param updateLeaseReq the update lease req
	 * @return the response entity
	 */
	@PutMapping("/updateLeaseStatus")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> updateLeaseStatus(@RequestBody UpdateLeaseStatusReq updateLeaseReq) {
		finLeaseService.updateLeaseStatus(updateLeaseReq);
		return new ResponseEntity<>(
				new CustomResponse("Lease status updated successfully", updateLeaseReq.getLeaseReferenceId()),
				HttpStatus.OK);
	}

	/**
	 * Fetch user detail.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping("/fetchProposerDetail/{userId}")
	@PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
	public ResponseEntity<?> fetchUserDetail(@PathVariable String userId) {
		return new ResponseEntity<>(finLeaseService.fetchUserDetail(userId), HttpStatus.OK);
	}

	/**
	 * Fetch all lease.
	 *
	 * @param status the status
	 * @return the response entity
	 */
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
	public ResponseEntity<?> fetchAllLease(@RequestParam String status) {
		return new ResponseEntity<>(finLeaseService.fetchLeaseList(status), HttpStatus.OK);
	}

	/**
	 * Download lease list.
	 *
	 * @param reqr the reqr
	 * @param response the response
	 * @param status the status
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping("/leaseExcel")
	public void downloadLeaseList(HttpServletRequest reqr, HttpServletResponse response, @RequestParam String status)
			throws IOException {
		response.setContentType(ApplicationConstants.APPLICATION_OCTET_TYPE);
		DateFormat dateFormatter = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
		String currentDateTime = dateFormatter.format(new Date());

		String headerValue = ApplicationConstants.XL_HEADER_KEY + currentDateTime + ApplicationConstants.FILE_TYPE;
		response.setHeader(ApplicationConstants.CONTNET_DISPOSITION, headerValue);
		List<LeaseResponseDTO> list = finLeaseService.fetchLeaseList(status);
		LeaseExporterClass excelExporter = new LeaseExporterClass(list, status);
		excelExporter.export(response);
	}
}
