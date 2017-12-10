package com.gwf.security.app.social.impl;

import com.gwf.security.core.social.SocialAuthenticationFilterPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/12/9.
 */
@Component
@Slf4j
public class AppSocialAuthenticationPostProcessor implements SocialAuthenticationFilterPostProcessor{

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Override
    public void process(SocialAuthenticationFilter filter) {
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
    }
}
