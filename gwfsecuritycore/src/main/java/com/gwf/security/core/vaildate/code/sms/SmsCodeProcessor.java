package com.gwf.security.core.vaildate.code.sms;

import com.gwf.security.core.vaildate.code.ValidateCode;
import com.gwf.security.core.vaildate.code.impl.AbstractValidateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/11/24.
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateProcessor<ValidateCode>{

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 发送图片验证码,将其写入响应中
     * @param request
     * @param imageCode
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode imageCode) throws IOException, ServletRequestBindingException {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        smsCodeSender.send(mobile,imageCode.getCode());
    }
}
