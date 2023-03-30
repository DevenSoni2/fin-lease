package com.nagarro.fin.lease.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.fin.lease.app.payload.request.LoginRequest;
import com.nagarro.fin.lease.app.payload.request.TokenRefreshRequest;
import com.nagarro.fin.lease.app.payload.request.UserRequestDTO;
import com.nagarro.fin.lease.app.payload.response.CustomResponse;
import com.nagarro.fin.lease.app.payload.response.JwtResponse;
import com.nagarro.fin.lease.app.service.UserService;

/**
 * The Class UserController.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Register user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the response entity
	 */
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		String userId = userService.registerUser(userRequestDTO);
		return new ResponseEntity<>(new CustomResponse("User registered successfully", userId), HttpStatus.CREATED);
	}

	/**
	 * Authenticate user.
	 *
	 * @param loginRequest the login request
	 * @return the response entity
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = userService.authenticateUser(loginRequest);
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

	}
	
	/**
	 * Refreshtoken.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		return new ResponseEntity<>(userService.refreshToken(request), HttpStatus.OK);
	}
	
}
