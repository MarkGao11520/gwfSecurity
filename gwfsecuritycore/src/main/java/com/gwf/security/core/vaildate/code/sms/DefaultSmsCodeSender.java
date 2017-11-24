package com.gwf.security.core.vaildate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        log.info("向手机【{}】发送验证码【{}】",mobile,code);
    }
}
