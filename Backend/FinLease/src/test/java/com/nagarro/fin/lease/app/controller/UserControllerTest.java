package com.nagarro.fin.lease.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.fin.lease.app.payload.request.LoginRequest;
import com.nagarro.fin.lease.app.payload.request.UserRequestDTO;
import com.nagarro.fin.lease.app.payload.response.JwtResponse;
import com.nagarro.fin.lease.app.service.UserService;

/**
 * The Class UserControllerTest.
 */
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	
	/** The controller. */
	@InjectMocks
	private UserController controller;
	
	/** The service. */
	@Mock
	private UserService service;

	/**
	 * Test register user.
	 */
	@Test
	void testRegisterUser() {
		Mockito.when(service.registerUser(Mockito.any())).thenReturn("CUS_00001");
		controller.registerUser(new UserRequestDTO());
		Mockito.verify(service, Mockito.times(1)).registerUser(Mockito.any());
	}

	/**
	 * Test authenticate user.
	 */
	@Test
	void testAuthenticateUser() {
		Mockito.when(service.authenticateUser(Mockito.any()))
				.thenReturn(new JwtResponse("abc", "abc", "CUS_0001", "USERNAME",1));
		controller.authenticateUser(new LoginRequest());
		Mockito.verify(service, Mockito.times(1)).authenticateUser(Mockito.any());
	}
}
