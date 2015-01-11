/**
 * 
 */
package org.asn.springmvc.core.exception;

/**
 * @author Abhishek
 *
 */
public class InvalidMemberException extends Exception {

	private String msg;
	
	public InvalidMemberException(String msg){
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {		
		return this.msg;
	}
		
}
