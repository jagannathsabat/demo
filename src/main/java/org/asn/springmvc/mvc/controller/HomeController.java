package org.asn.springmvc.mvc.controller;

 import org.asn.springmvc.core.entities.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
    	LOG.info("GET /");
        model.addAttribute("newMember", new Member());
        return "index";
    }
    
}
