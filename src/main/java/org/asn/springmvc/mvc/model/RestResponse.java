/**
 * 
 */
package org.asn.springmvc.mvc.model;

/**
 * @author Abhishek
 *
 */
public class RestResponse {

	private REQ success;
	private Object responseContent;
	
	public RestResponse(){}	
	
	public RestResponse(REQ success, Object responseContent) {
		super();
		this.success = success;
		this.responseContent = responseContent;
	}


	public REQ getSuccess() {
		return success;
	}


	public void setSuccess(REQ success) {
		this.success = success;
	}


	public Object getResponseContent() {
		return responseContent;
	}


	public void setResponseContent(Object responseContent) {
		this.responseContent = responseContent;
	}


	public enum REQ {
		SUCCESS("1"), FAILED("0");
		
		private String val;
		REQ(String val){
			this.val = val;
		}
		
		public String getVal(){
			return val;
		}
		
	}
}
