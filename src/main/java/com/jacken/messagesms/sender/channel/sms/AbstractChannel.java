package com.jacken.messagesms.sender.channel.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.jacken.messagesms.exception.MessageException;
import com.jacken.messagesms.utils.HttpExecuter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractChannel {
	private static Log logger = LogFactory.getLog(AbstractChannel.class);
	String channelName;

	public abstract ChannelConfig getConfig();

	public abstract RequestMessage getMessage(String mobileNo, String smsContext);

	public void send(String mobileNo, String smsContext) throws MessageException {
		String requestJson = getMessage(mobileNo, smsContext).getRequestContext();
		String requestURL = getConfig().getUrl();
		HttpExecuter httpExecuter = new HttpExecuter(requestURL);
		httpExecuter.setRequestBody(requestJson);
		String response = httpExecuter.doPost();
		logger.info("请求创蓝发送短信响应结果：" + response);
		JSONObject reJsonObject = JSON.parseObject(response);
		int code = Integer.valueOf(reJsonObject.get("code").toString());
		if (code != 0) {
			logger.error("创蓝短信发送失败，response：" + response);
			throw new MessageException(5000, "短信发送失败");
		}
	}

}
