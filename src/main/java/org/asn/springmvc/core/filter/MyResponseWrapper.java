/**
 * 
 */
package org.asn.springmvc.core.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Abhishek
 *
 */
public class MyResponseWrapper implements HttpServletResponse {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private HttpServletResponse response;
	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private PrintWriter writer = new PrintWriter(bos);    

    
    public MyResponseWrapper(HttpServletResponse response) {
        this.response = response;
        LOG.info("MyResponseWrapper instance created.");
    }

    public String getMobileEncryptedResponseContent(){
    	return this.bos.toString();
    }
    
	@Override
	public String getCharacterEncoding() {
		return this.response.getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		return this.response.getContentType();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		LOG.info("getOutputStream() invoked.");
		return new ServletOutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				bos.write(b);
			}
		};
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		LOG.info("getWriter() invoked.");
		return writer;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		this.response.setCharacterEncoding(charset);
	}

	@Override
	public void setContentLength(int len) {
		this.response.setContentLength(len);
	}

	@Override
	public void setContentType(String type) {
		this.response.setContentType(type);
	}

	@Override
	public void setBufferSize(int size) {
		this.response.setBufferSize(size);
	}

	@Override
	public int getBufferSize() {
		return this.response.getBufferSize();
	}

	@Override
	public void flushBuffer() throws IOException {
		this.response.flushBuffer();
	}

	@Override
	public void resetBuffer() {
		this.response.resetBuffer();
	}

	@Override
	public boolean isCommitted() {
		return this.response.isCommitted();
	}

	@Override
	public void reset() {
		this.response.reset();
	}

	@Override
	public void setLocale(Locale loc) {
		this.response.setLocale(loc);
	}

	@Override
	public Locale getLocale() {
		return this.response.getLocale();
	}

	@Override
	public void addCookie(Cookie cookie) {
		this.response.addCookie(cookie);
	}

	@Override
	public boolean containsHeader(String name) {
		return this.response.containsHeader(name);
	}

	@Override
	public String encodeURL(String url) {
		return this.response.encodeURL(url);
	}

	@Override
	public String encodeRedirectURL(String url) {
		return this.response.encodeRedirectURL(url);
	}

	@Override
	public String encodeUrl(String url) {
		return this.response.encodeUrl(url);
	}

	@Override
	public String encodeRedirectUrl(String url) {
		return this.response.encodeRedirectUrl(url);
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		this.response.sendError(sc, msg);
	}

	@Override
	public void sendError(int sc) throws IOException {
		this.response.sendError(sc);
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		this.response.sendRedirect(location);
	}

	@Override
	public void setDateHeader(String name, long date) {
		this.response.setDateHeader(name, date);
	}

	@Override
	public void addDateHeader(String name, long date) {
		this.response.addDateHeader(name, date);
	}

	@Override
	public void setHeader(String name, String value) {
		this.response.setHeader(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		this.response.addHeader(name, value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		this.response.setIntHeader(name, value);
	}

	@Override
	public void addIntHeader(String name, int value) {
		this.response.addIntHeader(name, value);
	}

	@Override
	public void setStatus(int sc) {
		this.response.setStatus(sc);
	}

	@Override
	public void setStatus(int sc, String sm) {
		this.response.setStatus(sc, sm);
	}

	@Override
	public int getStatus() {
		return this.response.getStatus();
	}

	@Override
	public String getHeader(String name) {
		return this.response.getHeader(name);
	}

	@Override
	public Collection<String> getHeaders(String name) {
		return this.response.getHeaders(name);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return this.response.getHeaderNames();
	}

	public String getWebEncryptedResponseContent() {
		return this.bos.toString();
	}

}
