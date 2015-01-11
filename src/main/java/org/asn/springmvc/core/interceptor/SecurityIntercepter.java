/**
 * 
 */
package org.asn.springmvc.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Abhishek
 *
 */
@Component
public class SecurityIntercepter extends HandlerInterceptorAdapter {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LOG.info("URL path: {}",request.getServletPath());
		String url = request.getRequestURL().toString();
		if(url.contains("/user")){
			LOG.info("user page");
		}
		if(url.contains("/admin")){
			LOG.info("admin page");
		}
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		LOG.debug("Base URL: {}",baseURL);
		return true;
	}
	
}
