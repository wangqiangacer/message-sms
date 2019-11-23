package com.jacken.messagesms.controller;

import com.google.gson.Gson;
import com.jacken.messagesms.exception.MessageException;
import com.jacken.messagesms.result.ResultModel;
import com.jacken.messagesms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	SmsService smsService;

	@PostMapping("/smscode/send")
	public ResultModel<?> sendValidateCode(@RequestBody Map<String, String> body) throws MessageException {
		String mobileNo = body.get("mobileNo");
		String sceneCode = body.get("sceneCode");
		smsService.sendValidateCode(mobileNo, sceneCode);
		return ResultModel.createSuccess();
	}

    @PostMapping("/send/template")
    public ResultModel<?> sendTextMessage(@RequestBody Map<String, Object> body) throws MessageException {
        String mobileNo = body.get("mobileNo").toString();
        String messageContent = String.valueOf(body.get("messageContent"));
        Gson gson = new Gson();
        Map<String, String> variables = new HashMap<>(16);
        variables = gson.fromJson(messageContent, variables.getClass());
        String sceneCode = body.get("sceneCode").toString();
        smsService.sendTemplateMessage(mobileNo, variables, sceneCode);
        return ResultModel.createSuccess();
    }

	@PostMapping("/smscode/validate")
	public ResultModel<?> validateSmsCode(@RequestBody Map<String, String> body) throws MessageException {
		String mobileNo = body.get("mobileNo");
		String sceneCode = body.get("sceneCode");
		String smsCode = body.get("smsCode");
		boolean success = smsService.valiedateSmsCode(mobileNo, sceneCode, smsCode);
		if (success) {
			return ResultModel.createSuccess("验证码正确");
		}else {
			return ResultModel.createFail(2001, "短信验证码错误");
		}
	}
}
