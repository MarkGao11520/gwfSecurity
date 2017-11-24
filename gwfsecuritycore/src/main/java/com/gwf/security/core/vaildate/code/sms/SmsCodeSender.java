package com.gwf.security.core.vaildate.code.sms;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}
