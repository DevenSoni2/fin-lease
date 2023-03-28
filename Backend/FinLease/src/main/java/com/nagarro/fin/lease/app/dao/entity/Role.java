package com.nagarro.fin.lease.app.dao.entity;

import javax.persistence.*;

/**
 * The Class Role.
 */
@Entity
@Table(name = "roles")
public class Role {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The name. */
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	/**
	 * Instantiates a new role.
	 */
	public Role() {

	}

	/**
	 * Instantiates a new role.
	 *
	 * @param name the name
	 */
	public Role(ERole name) {
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public ERole getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(ERole name) {
		this.name = name;
	}
}