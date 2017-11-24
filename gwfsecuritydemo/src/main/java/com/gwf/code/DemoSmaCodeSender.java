package com.gwf.code;

import com.gwf.security.core.vaildate.code.sms.SmsCodeSender;

/**
 * Created by gaowenfeng on 2017/11/24.
 */
// @Component
public class DemoSmaCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        System.out.println("更高级的短信验证码");
    }
}
