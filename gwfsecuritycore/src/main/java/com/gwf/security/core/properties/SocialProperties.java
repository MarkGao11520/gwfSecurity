package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/11/25.
 */
@Data
public class SocialProperties {
    private QQProperties qq = new QQProperties();

    private WeixinProperties weixin = new WeixinProperties();

    private String filterProcessesUrl = "/auth";
}
