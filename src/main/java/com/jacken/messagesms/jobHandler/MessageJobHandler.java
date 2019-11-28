package com.jacken.messagesms.jobHandler;

import com.jacken.messagesms.service.SmsService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@JobHandler(value = "MessageJobHandler")
@Component
@Slf4j
@SuppressWarnings("All")
public class MessageJobHandler extends IJobHandler {

    @Autowired
    private SmsService smsService;
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        smsService.sendValidateCode("18483678377","login-or-register");
        return SUCCESS;
    }
}
