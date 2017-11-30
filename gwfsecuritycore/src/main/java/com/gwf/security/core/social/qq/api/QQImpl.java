package com.gwf.security.core.social.qq.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/11/25.
 * 多实例，不能声明成Spring的bean
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ  {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken,String appId){
        super(accessToken, TokenStrategy.OAUTH_TOKEN_PARAMETER);

        this.appId = appId;

        String result = getRestTemplate().getForObject(String.format(URL_GET_OPENID,accessToken),String.class);

        log.info(result);

        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }


    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_USER_INFO,appId,openId);

        String result = getRestTemplate().getForObject(url,String.class);

        log.info(result);

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result,QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
