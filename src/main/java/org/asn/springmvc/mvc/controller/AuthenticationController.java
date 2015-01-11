/**
 * 
 */
package org.asn.springmvc.mvc.controller;

import javax.servlet.http.HttpSession;

import org.asn.springmvc.core.entities.User;
import org.asn.springmvc.mvc.model.LoginModel;
import org.asn.springmvc.mvc.model.RestResponse;
import org.asn.springmvc.mvc.model.RestResponse.REQ;
import org.asn.springmvc.mvc.model.UserRegisterationModel;
import org.asn.springmvc.mvc.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Abhishek
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)	
	public ModelAndView registerView(Model model) {
		LOG.info("GET came to /auth/register");		
		model.addAttribute("REGISTER_MODEL", new UserRegisterationModel());		
		return new ModelAndView("register");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)	
	public ModelAndView register(Model model, @RequestBody UserRegisterationModel regModel,
			HttpSession session) {
		LOG.info("POST came to /auth/register");
		RestResponse response = null;
		boolean result = userService.registerUser(regModel);
		String msg = (result)? "Registeraton Successfull" : "Try again";
		response = new RestResponse(REQ.SUCCESS, msg);
		model.addAttribute(response);
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)	
	public ModelAndView login(Model model) {
		LOG.info("GET came to /auth/login");				
		model.addAttribute("LOGIN_MODEL", new LoginModel());
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)	
	public ModelAndView login(Model model, @RequestBody LoginModel loginModel,
			HttpSession session) {
		LOG.info("POST came to /auth/login");
		RestResponse response = null;
		User dbUser = userService.findByUserName(loginModel.getUsername());
		if(dbUser!=null && dbUser.getJ_password().equals(loginModel.getPassword())){
			response = new RestResponse(REQ.SUCCESS, "Login Successfull.");
		}else{
			response = new RestResponse(REQ.SUCCESS, "Invalid credentials.");
		}
		
		model.addAttribute(response);
		userService.userLoggedIn(loginModel, session);
		return new ModelAndView();
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(Model model, HttpSession session){
		LOG.info("GET logout");
		userService.userLoggedOut(session);
		return new ModelAndView();
	}	
}
