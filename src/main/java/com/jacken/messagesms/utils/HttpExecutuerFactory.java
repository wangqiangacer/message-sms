package com.jacken.messagesms.utils;
/**
 * 
 * @author honglongsun
 *
 */
public class HttpExecutuerFactory {

	public static HttpExecuter getDefaultExecuter(String url) {
		HttpExecuter httpExecuter = new HttpExecuter(url);
		return httpExecuter;
	}
	public static  HttpExecuter getAutoAdminTokenExecuter(String url) {
		return new HttpExecuter(url);
	}

}
