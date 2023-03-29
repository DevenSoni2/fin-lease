package com.nagarro.fin.lease.app.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.fin.lease.app.dao.entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, String>  {
	Optional<Customer> findByUserName(String username);
}
