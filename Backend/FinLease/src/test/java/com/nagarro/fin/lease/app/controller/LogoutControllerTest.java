package com.nagarro.fin.lease.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nagarro.fin.lease.app.service.UserService;

@ExtendWith(MockitoExtension.class)
class LogoutControllerTest {
	@InjectMocks
	private LogoutController controller;
	@Mock
	private UserService service;
	
	@Test
	void testLogoutUser() {
		Mockito.doNothing().when(service).signout();
		controller.logoutUser();
		Mockito.verify(service, Mockito.times(1)).signout();
	}

}
