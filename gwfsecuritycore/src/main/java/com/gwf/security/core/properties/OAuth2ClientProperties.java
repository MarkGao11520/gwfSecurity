package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/12/11.
 */
@Data
public class OAuth2ClientProperties {
    private String clientId;
    private String clientSecret;
    private int accessTokenValidateSeconds = 7000;
}
