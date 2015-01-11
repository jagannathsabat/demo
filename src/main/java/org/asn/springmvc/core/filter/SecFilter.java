/**
 * 
 */
package org.asn.springmvc.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.asn.springmvc.api.UserPreference;
import org.asn.springmvc.core.entities.User.USER_ROLE;
import org.asn.springmvc.mvc.model.RestResponse;
import org.asn.springmvc.mvc.model.RestResponse.REQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Abhishek
 * 
 */
@Component("secFilter")
public class SecFilter implements Filter {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("init filter");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		LOG.info("doFilter filter");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		HttpSession httpSession = httpServletRequest.getSession(false);
		UserPreference userPreference = null;
		String user = null;
		USER_ROLE userRole = null;

		if (httpSession != null) {
			// LOG.info("is session new: {}", httpSession.isNew());
			userPreference = (UserPreference) httpSession.getAttribute("AUTHENTICATED_USER");
			if (userPreference != null) {
				user = userPreference.getName();
				userRole = userPreference.getAssignedRole();
			}
		}

		// wrap around the original request and response
		MyRequestWrapper reqWrap = new MyRequestWrapper(user, userRole,	httpServletRequest);
		MyResponseWrapper resWrap = new MyResponseWrapper(httpServletResponse);

		boolean isAllowedToAccess = checkUserCanAccess(userPreference, resWrap);

		if (!isAllowedToAccess) {
			httpServletResponse.getOutputStream().write(
					resWrap.getMobileEncryptedResponseContent().getBytes());
			return;
		}

		// pass the wrappers on to the next entry
		chain.doFilter(reqWrap, resWrap);

		// String url = httpServletRequest.getRequestURL().toString();
		String responseData = resWrap.getMobileEncryptedResponseContent();
		// LOG.debug("WEB, response from controller handler:{}",responseData);

		// write response data to original response
		httpServletResponse.getOutputStream().write(responseData.getBytes());

	}

	private boolean checkUserCanAccess(UserPreference userPreference,
			MyResponseWrapper resWrap) throws IOException, ServletException {

		if (userPreference != null) {
			LOG.debug("user[{},{}] is allowed to access.",
					userPreference.getName(), userPreference.getAssignedRole());
			return true;
		} else {
			LOG.warn("user is not logged in yet, access restricted");
			RestResponse response = new RestResponse(REQ.FAILED,
					"Access restricted, Please login");
			ObjectMapper objectMapper = new ObjectMapper();
			String strRes = objectMapper.writer().writeValueAsString(response);
			resWrap.getOutputStream().write(strRes.getBytes());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		LOG.info("destroy filter");
	}

}
