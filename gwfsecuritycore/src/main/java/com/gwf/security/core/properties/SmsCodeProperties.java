package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;
    private String url;
}
