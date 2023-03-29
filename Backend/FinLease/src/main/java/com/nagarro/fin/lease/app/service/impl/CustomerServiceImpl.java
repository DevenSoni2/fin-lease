package com.nagarro.fin.lease.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nagarro.fin.lease.app.dao.entity.Customer;
import com.nagarro.fin.lease.app.dao.repository.CustomerRepository;
import com.nagarro.fin.lease.app.exceptions.GenericException;
import com.nagarro.fin.lease.app.payload.request.CustomerRequestDto;
import com.nagarro.fin.lease.app.payload.response.CustomerResponseDTO;
import com.nagarro.fin.lease.app.service.CustomerService;

/**
 * The Class CustomerServiceImpl.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Fetch customer detail.
	 *
	 * @param customerId the customer id
	 * @return the customer response DTO
	 */
	@Override
	public CustomerResponseDTO fetchCustomerDetail(String customerId) {
		Optional<Customer> opCustomer = customerRepository.findById(customerId);
		if (!opCustomer.isPresent()) {
			throw new GenericException("Customer doesn't exist with customer id:  " + customerId, HttpStatus.NOT_FOUND);
		}
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setBuisnessUnit(opCustomer.get().getBuisnessUnit());
		customerResponseDTO.setCustomerId(opCustomer.get().getId());
		customerResponseDTO.setName(opCustomer.get().getName());
		customerResponseDTO.setUserName(opCustomer.get().getUserName());
		return customerResponseDTO;
	}

	/**
	 * Register customer.
	 *
	 * @param customerRequestDto the customer request dto
	 * @return the string
	 */
	@Override
	public String registerCustomer(CustomerRequestDto customerRequestDto) {
		Optional<Customer> opCustomer = customerRepository.findByUserName(customerRequestDto.getUserName());
		if (opCustomer.isPresent()) {
			throw new GenericException("Customer already exist with user name:  " + customerRequestDto.getUserName(),
					HttpStatus.CONFLICT);
		}
		Customer customer = new Customer();
		customer.setBuisnessUnit(customerRequestDto.getBuisnessUnit());
		customer.setName(customerRequestDto.getName());
		customer.setUserName(customerRequestDto.getUserName());
		customerRepository.save(customer);
		return customer.getId();
	}

}
