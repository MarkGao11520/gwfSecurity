package com.gwf.security.app.jwt;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenIterator;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/12/15.
 */
public class MyJwtTokenEnhancer implements TokenEnhancer{
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken auth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> info = new HashedMap();
        info.put("company","jd");

        ((DefaultOAuth2AccessToken) auth2AccessToken).setAdditionalInformation(info);

        return auth2AccessToken;
    }
}
