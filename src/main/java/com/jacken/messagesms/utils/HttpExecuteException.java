package com.jacken.messagesms.utils;


import com.jacken.messagesms.exception.BaseRuntimeException;

/**
 * 
 * @author honglongsun
 *
 */
public class HttpExecuteException extends BaseRuntimeException {
 

	public HttpExecuteException(int code, String message) {
		super(code, message);
	}

	private static final long serialVersionUID = 101068140165250455L;


	 
}
