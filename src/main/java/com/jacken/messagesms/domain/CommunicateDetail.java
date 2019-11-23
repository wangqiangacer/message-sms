package com.jacken.messagesms.domain;


public class CommunicateDetail extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107441039691240006L;
	
	private String code;
	private String responseDetail;
	private String target;
	private String requestDetail;
	private String commMode;
	
	
	public String getCommMode() {
		return commMode;
	}
	public void setCommMode(String commMode) {
		this.commMode = commMode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getResponseDetail() {
		return responseDetail;
	}
	public void setResponseDetail(String responseDetail) {
		this.responseDetail = responseDetail;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRequestDetail() {
		return requestDetail;
	}
	public void setRequestDetail(String requestDetail) {
		this.requestDetail = requestDetail;
	}
	 
	
	

}
