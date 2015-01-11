/**
 * 
 */
package org.asn.springmvc.mvc.service.api.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.asn.springmvc.api.UserPreference;
import org.asn.springmvc.core.entities.User;
import org.asn.springmvc.core.entities.User.USER_ROLE;
import org.asn.springmvc.core.repo.UserRepository;
import org.asn.springmvc.mvc.model.LoginModel;
import org.asn.springmvc.mvc.model.UserRegisterationModel;
import org.asn.springmvc.mvc.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Abhishek
 *
 */
@Service
@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired private UserRepository userRepository;
	
	public UserServiceImpl(){
		LOG.info("User service instance created.");
	}
	
	@Override
	public void userLoggedIn(LoginModel user, HttpSession session) {
		LOG.info("setting user preference in session");
		UserPreference userData = userRepository.findByUsername(user.getUsername());
		UserPreference userPreference = (UserPreference) session.getAttribute("AUTHENTICATED_USER");
		if(userPreference!=null){
			LOG.error("A user:{} already logged in", userPreference.getName());
			throw new IllegalArgumentException("Already logged in");
		}
		session.setAttribute("AUTHENTICATED_USER", userData);
	}

	@Override
	public void userLoggedOut(HttpSession session) {
		UserPreference userPreference = (UserPreference) session.getAttribute("AUTHENTICATED_USER");
		LOG.info("deleting user:{} from session", userPreference.getName());		
		session.invalidate();
	}

	@Transactional
	@Override
	public boolean registerUser(UserRegisterationModel regModel) {
		User user = new User(regModel.getUserName(), regModel.getPassword());
		user.setUserRole(USER_ROLE.ROLE_USER);
		if(userRepository.exists(regModel.getUserName())){
			throw new IllegalArgumentException("User already registered.");
		}
		userRepository.save(user);		
		return userRepository.exists(regModel.getUserName());
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
