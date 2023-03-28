package com.nagarro.fin.lease.app.dao.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Parameter;

import com.nagarro.fin.lease.app.config.StringPrefixedSequenceIdGenerator;

import org.hibernate.annotations.GenericGenerator;

/**
 * The Class User.
 */
@Entity
@Table(name = "users")
public class User {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.nagarro.fin.lease.app.config.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CUS_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;

	/** The username. */
	@NotBlank
	@Size(max = 20)
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	/** The name. */
	@NotBlank
	@Size(max = 20)
	private String name;

	/** The buisness unit. */
	@NotBlank
	@Size(max = 20)
	private String buisnessUnit;

	/** The password. */
	@NotBlank
	@Size(max = 120)
	private String password;

	/** The roles. */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param name the name
	 * @param buisnessUnit the buisness unit
	 */
	public User(String username, String password, String name, String buisnessUnit) {
		this.username = username;
		this.password = password;
		this.buisnessUnit = buisnessUnit;
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
