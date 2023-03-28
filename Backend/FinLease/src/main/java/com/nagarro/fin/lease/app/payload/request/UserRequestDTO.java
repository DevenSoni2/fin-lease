package com.nagarro.fin.lease.app.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The Class UserRequestDTO.
 */
public class UserRequestDTO {
	
	/** The username. */
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
	
	/** The role. */
	private String role;
	
	/** The password. */
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	/** The name. */
	private String name;

	/** The buisness unit. */
	@NotBlank
	@Size(min = 3, max = 50)
	private String buisnessUnit;

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the buisness unit.
	 *
	 * @return the buisness unit
	 */
	public String getBuisnessUnit() {
		return buisnessUnit;
	}

	/**
	 * Sets the buisness unit.
	 *
	 * @param buisnessUnit the new buisness unit
	 */
	public void setBuisnessUnit(String buisnessUnit) {
		this.buisnessUnit = buisnessUnit;
	}

}
