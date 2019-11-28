package com.jacken.messagesms.jobHandler;

import com.jacken.messagesms.dao.UserRepository;
import com.jacken.messagesms.domain.User;
import com.jacken.messagesms.service.SmsService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@JobHandler(value = "MessageJobHandler")
@Component
@Slf4j
@SuppressWarnings("All")
public class MessageJobHandler extends IJobHandler {

    @Autowired
    private SmsService smsService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        List<User> list = userRepository.findAllUser();
        for (User user : list) {
            smsService.sendValidateCode(user.getMobileNo(),"login-or-register");
        }
        return SUCCESS;
    }
}
