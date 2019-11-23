package com.jacken.messagesms.exception;

import com.jacken.messagesms.result.ResultModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ControllerExceptionHandler
 * @Description: 处理短信发送发送异常
 * @Author chenjie
 * @Date 2019/11/8
 * @Version V1.0
 **/
@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(MessageException.class)
    public @ResponseBody
    ResultModel<?> JavaScriptExceptionHandle(MessageException e) {
        return ResultModel.createFail(107, "手机号码格式有误");
    }
}
