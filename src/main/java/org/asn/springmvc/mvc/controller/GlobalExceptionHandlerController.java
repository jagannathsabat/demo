/**
 * 
 */
package org.asn.springmvc.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Abhishek
 *
 */
@ControllerAdvice
public class GlobalExceptionHandlerController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	 
	@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		LOG.error("Error occured: {}", e.getMessage(), e);
        // setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
	
	@ExceptionHandler(value = IllegalArgumentException.class)
    public ModelAndView invalidArgumentErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		LOG.error("Invalid argument: {}", e.getMessage(), e);
        // setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
