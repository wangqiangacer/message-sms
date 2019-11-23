package com.jacken.messagesms.sender.channel.sms.chuanglan;

import com.alibaba.fastjson.JSONObject;
import com.jacken.messagesms.sender.channel.sms.ChannelConfig;
import com.jacken.messagesms.sender.channel.sms.RequestMessage;


public class ChuangLanMessage extends RequestMessage {

	 
	public ChuangLanMessage(String mobileNo, String smsContext, ChannelConfig channelConfig) {
		super(mobileNo, smsContext, channelConfig);
	}

	@Override
	public String getRequestContext() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", this.smsContext);
		jsonObject.put("phone", this.mobileNo);
		if (this.channelConfig instanceof ChuangLanChannelConfig) {
			jsonObject.put("password", ((ChuangLanChannelConfig) this.channelConfig).getPassword());
			jsonObject.put("account", ((ChuangLanChannelConfig) this.channelConfig).getAccount());
		}else if (this.channelConfig instanceof ChuangLan2ChannelConfig) {
			jsonObject.put("password", ((ChuangLan2ChannelConfig) this.channelConfig).getPassword());
			jsonObject.put("account", ((ChuangLan2ChannelConfig) this.channelConfig).getAccount());
		}
		return jsonObject.toJSONString();
	}

}
