package com.jacken.messagesms.sender.channel.sms;


import com.jacken.messagesms.sender.channel.sms.chuanglan.Chuanglan2Channel;
import com.jacken.messagesms.sender.channel.sms.chuanglan.ChuanglanChannel;

public class ChannelFactory {

	public static AbstractChannel getSmsSender(String channelCode) {

		if (channelCode.equals("chuanglan")) {
			return new ChuanglanChannel();
		}
		if (channelCode.equals("chuanglan2")) {
			return new Chuanglan2Channel();
		}
		return null;
	}
}
