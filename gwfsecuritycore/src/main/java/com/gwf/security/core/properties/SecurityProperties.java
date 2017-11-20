package com.gwf.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@ConfigurationProperties(prefix = "gwf.security")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
}
