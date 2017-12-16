package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/12/11.
 */
@Data
public class OAuth2Properties {
    private OAuth2ClientProperties[] clients = {};
    private String storeType;
    private String jwtSigningKey = "gwf";
}
