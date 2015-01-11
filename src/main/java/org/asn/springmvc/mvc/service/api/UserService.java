/**
 * 
 */
package org.asn.springmvc.mvc.service.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.asn.springmvc.core.entities.User;
import org.asn.springmvc.mvc.model.LoginModel;
import org.asn.springmvc.mvc.model.UserRegisterationModel;

/**
 * @author Abhishek
 *
 */
public interface UserService {

	void userLoggedIn(LoginModel user, HttpSession session);
	void userLoggedOut(HttpSession session);
	boolean registerUser(UserRegisterationModel regModel); 
	User findByUserName(String username);
	List<User> findAll();
}
