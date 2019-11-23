package com.jacken.messagesms.sender.channel.sms;

public abstract class RequestMessage {

	protected String mobileNo;
	protected String smsContext;
	protected ChannelConfig channelConfig;

	public RequestMessage(String mobileNo, String smsContext, ChannelConfig channelConfig) {
		this.mobileNo = mobileNo;
		this.smsContext = smsContext;
		this.channelConfig = channelConfig;
	}

	public abstract String getRequestContext();
}
