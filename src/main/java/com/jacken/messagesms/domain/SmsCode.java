package com.jacken.messagesms.domain;


import java.util.Date;

public class SmsCode extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -238520835062112799L;
	
	private String code;
	private String mobile;
	private Date expireTime;
	private Date sendTime;
	private Integer expireDuration;
	private String serviceCode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getExpireDuration() {
		return expireDuration;
	}
	public void setExpireDuration(Integer expireDuration) {
		this.expireDuration = expireDuration;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	
	
	
}
