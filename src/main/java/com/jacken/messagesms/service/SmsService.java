package com.jacken.messagesms.service;


import com.jacken.messagesms.dao.SmsTemplateRepository;
import com.jacken.messagesms.domain.SmsTemplate;
import com.jacken.messagesms.exception.MessageException;
import com.jacken.messagesms.sender.channel.sms.AbstractChannel;
import com.jacken.messagesms.sender.channel.sms.ChannelFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Log4j2
public class SmsService {

	@Value("${sms-code.validate-mock:false}")
	Boolean valiedateMockMode;

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	SmsTemplateRepository smsTemplateRepository;

	public void sendValidateCode(String mobileNo, String sceneCode) throws MessageException {
		String validateCode = generateValidateCode();
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("code", validateCode);
		sendTemplateMessage(mobileNo, variables, sceneCode);
		String validateCodeKey = getSmsCodeKey(mobileNo, sceneCode);
		stringRedisTemplate.opsForValue().set(validateCodeKey, validateCode);
		// TODO 短信验证码落库
	}

	private String getSmsCodeKey(String mobileNo, String sceneCode) {
		String validateCodeKey = "sms:code:" + mobileNo + ":" + sceneCode;
		return validateCodeKey;
	}

	private String generateValidateCode() {

		return String.format("%04d", 5201314);
	}

	public void sendTextMessage(String mobileNo, String smsMessage, String sceneCode) throws MessageException {
		AbstractChannel channel = ChannelFactory.getSmsSender("chuanglan2");
		channel.send(mobileNo, smsMessage);
	}

	public void sendTemplateMessage(String mobileNo, Map<String, String> variables, String sceneCode)
			throws MessageException {

		SmsTemplate smsTemplate = smsTemplateRepository.selectBySceneCode(sceneCode);
		if (smsTemplate == null) {
			throw new MessageException(5000, "场景码模板不存在");
		}
		String template = smsTemplate.getTemplate();
		String templateContext;
		try {
			templateContext = setTemplateVariables(template, variables);
		} catch (Exception e) {
			throw new MessageException(5001, "短信模板和变量参数不匹配");
		}
		AbstractChannel channel = ChannelFactory.getSmsSender("chuanglan");
		channel.send(mobileNo, templateContext);
	}

	private String setTemplateVariables(String template, Map<String, String> variables) {
		for (Map.Entry<String, String> variable : variables.entrySet()) {
			String key = variable.getKey();
			String value = variable.getValue();
			String varName = "$" + "{" + key + "}";
			template = template.replace(varName, value);
		}
		return template;
	}

	public boolean valiedateSmsCode(String mobileNo, String sceneCode, String code) {
		log.info("valiedateSmsCode:{},{},{}", mobileNo, sceneCode, code);
		if (valiedateMockMode) {
			if (valiedateMock(code)) {
				return true;
			}
		}
		String key = getSmsCodeKey(mobileNo, sceneCode);
		String cachedCode = stringRedisTemplate.opsForValue().get(key);
		if (cachedCode != null && cachedCode.equals(code)) {
			return true;
		} else {
			log.info("cachedCode:" + cachedCode);
			return false;
		}
	}

	public boolean valiedateMock(String code) {
		if (code.equals("1234")) {
			return true;
		} else {
			return false;
		}
	}
}
