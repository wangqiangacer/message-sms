package com.jacken.messagesms.exception;

public class BaseException extends Exception {
 
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;

	public BaseException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}
