/**
 * 
 */
package org.asn.springmvc.core.util;

import java.util.Collection;

import org.asn.springmvc.core.entities.User;
import org.springframework.stereotype.Service;

/**
 * @author Abhishek
 *
 */
@Service
public class PrediacteImpl implements Predicate<User> {

	@Override
	public User selectById(Collection<User> source, Long id) {
		User user = null;
		for(User u : source){
			if(u.getId().equals(id)){
				user = u;
				break;
			}				
		}
		return user;
	}

	@Override
	public User selectByUsername(Collection<User> source, String username) {
		User user = null;
		for(User u : source){
			if(u.getJ_username().equals(username)){
				user = u;
				break;
			}				
		}
		return user;
	}

}
