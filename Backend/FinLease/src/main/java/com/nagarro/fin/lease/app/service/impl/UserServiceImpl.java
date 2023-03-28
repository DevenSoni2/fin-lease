package com.nagarro.fin.lease.app.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.fin.lease.app.dao.entity.ERole;
import com.nagarro.fin.lease.app.dao.entity.RefreshToken;
import com.nagarro.fin.lease.app.dao.entity.Role;
import com.nagarro.fin.lease.app.dao.entity.User;
import com.nagarro.fin.lease.app.dao.repository.RoleRepository;
import com.nagarro.fin.lease.app.dao.repository.UserRepository;
import com.nagarro.fin.lease.app.exceptions.GenericException;
import com.nagarro.fin.lease.app.payload.request.LoginRequest;
import com.nagarro.fin.lease.app.payload.request.UserRequestDTO;
import com.nagarro.fin.lease.app.payload.response.JwtResponse;
import com.nagarro.fin.lease.app.security.jwt.JwtUtils;
import com.nagarro.fin.lease.app.security.services.RefreshTokenService;
import com.nagarro.fin.lease.app.security.services.UserDetailsImpl;
import com.nagarro.fin.lease.app.service.UserService;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {
	
	/** The role repository. */
	@Autowired
	private RoleRepository roleRepository;
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/** The jwt utils. */
	@Autowired
	private JwtUtils jwtUtils;
	
	/** The refresh token service. */
	@Autowired
	RefreshTokenService refreshTokenService;
	
	/** The encoder. */
	@Autowired
	PasswordEncoder encoder;

	/**
	 * Register user.
	 *
	 * @param userRequestDTO the user request DTO
	 * @return the string
	 */
	@Override
	@Transactional
	public String registerUser(@Valid UserRequestDTO userRequestDTO) {
		if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
			throw new GenericException("Username is already taken!", HttpStatus.CONFLICT);
		}
		// Create new user's account
		User user = new User(userRequestDTO.getUsername(), encoder.encode(userRequestDTO.getPassword()),
				userRequestDTO.getName(), userRequestDTO.getBuisnessUnit());
		Set<Role> roles = new HashSet<>();

		if (Objects.isNull(userRequestDTO.getRole())) {
			Role userRole = roleRepository.findByName(ERole.USER)
					.orElseThrow(() -> new GenericException("Role not exist!", HttpStatus.NOT_FOUND));
			roles.add(userRole);
		} else {
			if (userRequestDTO.getRole().equals(ERole.ADMIN.name())) {
				Role adminRole = roleRepository.findByName(ERole.ADMIN)
						.orElseThrow(() -> new GenericException("Role not exist", HttpStatus.NOT_FOUND));
				roles.add(adminRole);
			} else if (userRequestDTO.getRole().equals(ERole.USER.name())) {
				Role adminRole = roleRepository.findByName(ERole.USER)
						.orElseThrow(() -> new GenericException("Role not exist", HttpStatus.NOT_FOUND));
				roles.add(adminRole);
			} else {
				throw new GenericException("Role not exist!", HttpStatus.NOT_FOUND);
			}
		}
		user.setRoles(roles);
		userRepository.save(user);
		return user.getId();
	}

	/**
	 * Authenticate user.
	 *
	 * @param loginRequest the login request
	 * @return the jwt response
	 */
	@Override
	public JwtResponse authenticateUser(@Valid LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername());
	}

	/**
	 * Signout.
	 */
	@Override
	public void signout() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String userId = userDetails.getId();
		refreshTokenService.deleteByUserId(userId);

	}

}
