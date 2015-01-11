/**
 * 
 */
package org.asn.springmvc.mvc.controller;

import java.security.Principal;
import java.util.List;

import org.asn.springmvc.core.entities.User;
import org.asn.springmvc.mvc.model.RestResponse;
import org.asn.springmvc.mvc.model.RestResponse.REQ;
import org.asn.springmvc.mvc.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Abhishek
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String user(){
		return "user";
	}
	
	@RequestMapping(value="/get/users",method=RequestMethod.POST)	
	public ModelAndView getUsers(Model model, Principal principal){
		LOG.debug("{} accessing to /user/get/users", principal.getName());
		List<User> users = userService.findAll();
		LOG.debug("users: {}", users);
		model.addAttribute(users);
		return new ModelAndView();
	}

	@RequestMapping(value="/get/users-rest",method=RequestMethod.POST)	
	public ModelAndView getUsersJson(Model model, Principal principal){
		LOG.debug("{} accessing to /user/get/users-rest", principal.getName());
		List<User> users = userService.findAll();
		LOG.debug("users: {}", users);
		RestResponse response = new RestResponse();
		response.setSuccess(REQ.SUCCESS);
		response.setResponseContent(users);
		model.addAttribute(response);
		return new ModelAndView();
	}
}
