package com.jacken.messagesms.sender.channel.sms.chuanglan;


import com.jacken.messagesms.config.SpringApplicationContext;
import com.jacken.messagesms.sender.channel.sms.AbstractChannel;
import com.jacken.messagesms.sender.channel.sms.ChannelConfig;
import com.jacken.messagesms.sender.channel.sms.RequestMessage;

public class ChuanglanChannel extends AbstractChannel {

	@Override
	public ChannelConfig getConfig() {
		ChuangLanChannelConfig channelConfig = (ChuangLanChannelConfig) SpringApplicationContext.getApplicationContext()
				.getBean(ChuangLanChannelConfig.class);
		return channelConfig;
	}

	@Override
	public RequestMessage getMessage(String mobileNo, String smsContext) {
		return new ChuangLanMessage(mobileNo, smsContext, getConfig());
	}

}
