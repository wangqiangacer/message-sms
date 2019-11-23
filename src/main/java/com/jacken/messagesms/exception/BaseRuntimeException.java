package com.jacken.messagesms.exception;

public class BaseRuntimeException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;

	public BaseRuntimeException(int code, String message) {
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
