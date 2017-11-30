package com.gwf.security.core.social;

import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by gaowenfeng on 2017/11/30.
 */
@Data
public class GwfSpringSocialConfigurer extends SpringSocialConfigurer{

    private String filterProcessesUrl;

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}

