package com.nagarro.fin.lease.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nagarro.fin.lease.app.dao.entity.ERole;
import com.nagarro.fin.lease.app.dao.entity.RefreshToken;
import com.nagarro.fin.lease.app.dao.entity.Role;
import com.nagarro.fin.lease.app.dao.entity.User;
import com.nagarro.fin.lease.app.dao.repository.RoleRepository;
import com.nagarro.fin.lease.app.dao.repository.UserRepository;
import com.nagarro.fin.lease.app.exceptions.GenericException;
import com.nagarro.fin.lease.app.payload.request.LoginRequest;
import com.nagarro.fin.lease.app.payload.request.UserRequestDTO;
import com.nagarro.fin.lease.app.security.jwt.JwtUtils;
import com.nagarro.fin.lease.app.security.services.RefreshTokenService;
import com.nagarro.fin.lease.app.security.services.UserDetailsImpl;

/**
 * The Class UserServiceImplTest.
 * @author devensoni
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	/** The service impl. */
	@InjectMocks
	private UserServiceImpl serviceImpl;

	/** The refresh token service. */
	@Mock
	private RefreshTokenService refreshTokenService;

	/** The authentication manager. */
	@Mock
	private AuthenticationManager authenticationManager;

	/** The jwt utils. */
	@Mock
	private JwtUtils jwtUtils;

	/** The encoder. */
	@Mock
	private PasswordEncoder encoder;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/** The role repository. */
	@Mock
	private RoleRepository roleRepository;

	/**
	 * Test signout.
	 */
	@Test
	void testSignout() {
		Mockito.when(refreshTokenService.deleteByUserId(Mockito.anyString())).thenReturn(1);
		Authentication authentication = Mockito.mock(Authentication.class);
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(2);
		role.setName(ERole.EMPLOYEE);
		roles.add(role);
		User user = new User();
		user.setRoles(roles);
		user.setId("CUS_00001");
		Mockito.when(authentication.getPrincipal()).thenReturn(UserDetailsImpl.build(user));
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		serviceImpl.signout();
		Mockito.verify(refreshTokenService, Mockito.times(1)).deleteByUserId(Mockito.anyString());
	}

	/**
	 * Test authenticate user.
	 */
	@Test
	void testAuthenticateUser() {
		Authentication auth = Mockito.mock(Authentication.class);
		Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(auth);
		Mockito.when(jwtUtils.generateJwtToken(Mockito.any())).thenReturn("abc");
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setId(2);
		role.setName(ERole.EMPLOYEE);
		roles.add(role);
		User user = new User();
		user.setRoles(roles);
		user.setId("CUS_00001");
		Mockito.when(auth.getPrincipal()).thenReturn(UserDetailsImpl.build(user));
		Mockito.when(refreshTokenService.createRefreshToken(Mockito.anyString())).thenReturn(new RefreshToken());
		serviceImpl.authenticateUser(new LoginRequest());
	}

	/**
	 * Test register user already exist.
	 */
	@Test
	void testRegisterUserAlreadyExist() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);
		assertThrows(GenericException.class, () -> {
			UserRequestDTO userRequestDTO = new UserRequestDTO();
			userRequestDTO.setUsername("username");
			serviceImpl.registerUser(userRequestDTO);
		});
	}

	/**
	 * Test register user.
	 */
	@Test
	void testRegisterUser() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
		Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(Optional.of(new Role()));
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUsername("username");
		userRequestDTO.setPassword("abc@12");
		serviceImpl.registerUser(userRequestDTO);
	}

	/**
	 * Test register user role not found 1.
	 */
	@Test
	void testRegisterUserRoleNotFound1() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
		Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(Optional.empty());
		assertThrows(GenericException.class, () -> {
			UserRequestDTO userRequestDTO = new UserRequestDTO();
			userRequestDTO.setUsername("username");
			userRequestDTO.setPassword("abc@12");
			serviceImpl.registerUser(userRequestDTO);
		});
	}

	/**
	 * Test register user role not found 2.
	 */
	@Test
	void testRegisterUserRoleNotFound2() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
		Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(Optional.empty());
		assertThrows(GenericException.class, () -> {
			UserRequestDTO userRequestDTO = new UserRequestDTO();
			userRequestDTO.setUsername("username");
			userRequestDTO.setPassword("abc@12");
			userRequestDTO.setRole("ADMIN");
			serviceImpl.registerUser(userRequestDTO);
		});
	}

	/**
	 * Test register user role found.
	 */
	@Test
	void testRegisterUserRoleFound() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
		Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(Optional.of(new Role()));
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUsername("username");
		userRequestDTO.setPassword("abc@12");
		userRequestDTO.setRole("ADMIN");
		serviceImpl.registerUser(userRequestDTO);
	}

	/**
	 * Test register user role not found 3.
	 */
	@Test
	void testRegisterUserRoleNotFound3() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
		Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(Optional.empty());
		assertThrows(GenericException.class, () -> {
			UserRequestDTO userRequestDTO = new UserRequestDTO();
			userRequestDTO.setUsername("username");
			userRequestDTO.setPassword("abc@12");
			userRequestDTO.setRole("EMPLOYEE");
			serviceImpl.registerUser(userRequestDTO);
		});
	}

	/**
	 * Test register user role found 2.
	 */
	@Test
	void testRegisterUserRoleFound2() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
		Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(Optional.of(new Role()));
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUsername("username");
		userRequestDTO.setPassword("abc@12");
		userRequestDTO.setRole("EMPLOYEE");
		serviceImpl.registerUser(userRequestDTO);
	}

	/**
	 * Test register user role not found 4.
	 */
	@Test
	void testRegisterUserRoleNotFound4() {
		Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
		assertThrows(GenericException.class, () -> {
			UserRequestDTO userRequestDTO = new UserRequestDTO();
			userRequestDTO.setUsername("username");
			userRequestDTO.setPassword("abc@12");
			userRequestDTO.setRole("USER_NEW");
			serviceImpl.registerUser(userRequestDTO);
		});
	}
}
