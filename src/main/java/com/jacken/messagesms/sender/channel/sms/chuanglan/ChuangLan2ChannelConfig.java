package com.jacken.messagesms.sender.channel.sms.chuanglan;

import com.jacken.messagesms.sender.channel.sms.ChannelConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChuangLan2ChannelConfig extends ChannelConfig {
	@Value("${channel.sms.chuanglan2.url}")
	private String url;
	@Value("${channel.sms.chuanglan2.account}")
	private String account;
	@Value("${channel.sms.chuanglan2.password}")
	private String password;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 

}
