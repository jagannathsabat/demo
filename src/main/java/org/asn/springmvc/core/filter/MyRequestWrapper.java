/**
 * 
 */
package org.asn.springmvc.core.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.asn.springmvc.core.entities.User.USER_ROLE;

/**
 * this custom wrapper is able to read multiple times
 * 
 * @author Abhishek
 * 
 */
public final class MyRequestWrapper extends HttpServletRequestWrapper {

	private ByteArrayOutputStream cachedBytes;
	private String user;
	private USER_ROLE userRole;
	private HttpServletRequest realRequest;

	public MyRequestWrapper(String user, USER_ROLE userRole,
			HttpServletRequest request) {
		super(request);
		this.realRequest = request;
		this.user = user;
		this.userRole = userRole;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (cachedBytes == null)
			cacheInputStream();

		return new CachedServletInputStream();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	private void cacheInputStream() throws IOException {
		/*
		 * Cache the inputstream in order to read it multiple times. For
		 * convenience, I use apache.commons IOUtils
		 */
		cachedBytes = new ByteArrayOutputStream();
		IOUtils.copy(super.getInputStream(), cachedBytes);
	}

	/* An inputstream which reads the cached request body */
	public class CachedServletInputStream extends ServletInputStream {
		private ByteArrayInputStream input;

		public CachedServletInputStream() {
			/* create a new input stream from the cached request body */
			input = new ByteArrayInputStream(cachedBytes.toByteArray());
		}

		@Override
		public int read() throws IOException {
			return input.read();
		}
	}

	@Override
	public boolean isUserInRole(String role) {
		if (userRole == null) {
			return this.realRequest.isUserInRole(role);
		}
		return USER_ROLE.contains(role);
	}

	@Override
	public Principal getUserPrincipal() {
		if (this.user == null) {
			return realRequest.getUserPrincipal();
		}

		// make an anonymous implementation to just return our user
		return new Principal() {
			@Override
			public String getName() {
				return user;
			}
		};
	}
}
