package com.gwf.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * Created by gaowenfeng on 2017/12/9.
 */
public interface SocialAuthenticationFilterPostProcessor {
    void process(SocialAuthenticationFilter filter);
}
