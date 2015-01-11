/**
 * 
 */
package org.asn.springmvc.core.repo;

import org.asn.springmvc.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Abhishek
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select case when (count(u) > 0) then true else false end from User u where u.j_username = ?1")
	boolean exists(String username);
	
	@Query("select distinct u from User u where u.j_username = ?1")
	User findByUsername(String username);
}
