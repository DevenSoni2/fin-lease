package com.nagarro.fin.lease.app.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.fin.lease.app.dao.entity.User;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	Optional<User> findByUsername(String username);

	/**
	 * Exists by username.
	 *
	 * @param username the username
	 * @return the boolean
	 */
	Boolean existsByUsername(String username);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<User> findById(String id);
}