package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image =new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();
}
