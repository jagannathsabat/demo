/**
 * 
 */
package org.asn.springmvc.core.util;

import java.util.Collection;

/**
 * @author Abhishek
 *
 */
public interface Predicate<User> {

	User selectById(Collection<User> source, Long id);
	User selectByUsername(Collection<User> source, String username);
}
