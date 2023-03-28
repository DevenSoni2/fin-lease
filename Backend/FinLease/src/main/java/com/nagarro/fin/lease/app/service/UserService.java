package com.nagarro.fin.lease.app.service;

import javax.validation.Valid;

import com.nagarro.fin.lease.app.payload.request.LoginRequest;
import com.nagarro.fin.lease.app.payload.request.UserRequestDTO;
import com.nagarro.fin.lease.app.payload.response.JwtResponse;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Register user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the string
	 */
	String registerUser(@Valid UserRequestDTO userRequestDTO);

	/**
	 * Authenticate user.
	 *
	 * @param loginRequest the login request
	 * @return the jwt response
	 */
	JwtResponse authenticateUser(@Valid LoginRequest loginRequest);

	/**
	 * Signout.
	 */
	void signout();

}
