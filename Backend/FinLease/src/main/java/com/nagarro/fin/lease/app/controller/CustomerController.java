package com.nagarro.fin.lease.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.fin.lease.app.payload.request.CustomerRequestDto;
import com.nagarro.fin.lease.app.payload.response.CustomResponse;
import com.nagarro.fin.lease.app.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lease")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/registerCustomer")
	@PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
	public ResponseEntity<?> registerUser( @RequestBody CustomerRequestDto customerRequestDto) {
		String customerId = customerService.registerCustomer(customerRequestDto);
		return new ResponseEntity<>(new CustomResponse("Customer registered successfully", customerId), HttpStatus.CREATED);
	}
	@GetMapping("/fetchCustomerDetail")
	@PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
	public ResponseEntity<?> fetchCustomerDetail(@RequestParam String customerId){
		return new ResponseEntity<>(customerService.fetchCustomerDetail(customerId), HttpStatus.OK);
	}
}
