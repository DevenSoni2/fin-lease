package com.nagarro.fin.lease.app.service;

import com.nagarro.fin.lease.app.payload.request.CustomerRequestDto;
import com.nagarro.fin.lease.app.payload.response.CustomerResponseDTO;

/**
 * The Interface CustomerService.
 */
public interface CustomerService {

	/**
	 * Fetch customer detail.
	 *
	 * @param customerId the customer id
	 * @return the customer response DTO
	 */
	CustomerResponseDTO fetchCustomerDetail(String customerId);

	/**
	 * Register customer.
	 *
	 * @param customerRequestDto the customer request dto
	 * @return the string
	 */
	String registerCustomer(CustomerRequestDto customerRequestDto);

}
