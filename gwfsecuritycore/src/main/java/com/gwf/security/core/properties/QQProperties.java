package com.gwf.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Created by gaowenfeng on 2017/11/25.
 */
@Data
public class QQProperties extends SocialProperties{

    private String providerId = "qq";
}
