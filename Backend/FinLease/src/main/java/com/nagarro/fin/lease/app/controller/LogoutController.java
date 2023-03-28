package com.nagarro.fin.lease.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.fin.lease.app.service.UserService;

/**
 * The Class LogoutController.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lease")
public class LogoutController {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Logout user.
	 *
	 * @return the response entity
	 */
	@GetMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		userService.signout();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
