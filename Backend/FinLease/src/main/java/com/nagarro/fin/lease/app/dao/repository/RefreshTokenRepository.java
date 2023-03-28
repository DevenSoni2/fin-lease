package com.nagarro.fin.lease.app.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.nagarro.fin.lease.app.dao.entity.RefreshToken;
import com.nagarro.fin.lease.app.dao.entity.User;

/**
 * The Interface RefreshTokenRepository.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	/**
	 * Find by token.
	 *
	 * @param token the token
	 * @return the optional
	 */
	Optional<RefreshToken> findByToken(String token);

	/**
	 * Delete by user.
	 *
	 * @param user the user
	 * @return the int
	 */
	@Modifying
	int deleteByUser(User user);
}
