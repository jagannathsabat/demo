/**
 * 
 */
package org.asn.springmvc.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Abhishek
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(method=RequestMethod.GET)
	public String admin(Model model){
		return "admin";
	}
}
