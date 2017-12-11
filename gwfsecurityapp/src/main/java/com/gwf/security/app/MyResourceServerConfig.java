package com.gwf.security.app;

import com.gwf.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.gwf.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.gwf.security.core.properties.SecurityConstants;
import com.gwf.security.core.properties.SecurityProperties;
import com.gwf.security.core.vaildate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by gaowenfeng on 2017/12/3.
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter{

    /**
     * 自定义配置参数类
     */
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHandler;

    /**
     * qq登录配置
     */
    @Autowired
    private SpringSocialConfigurer gwfSocialConfigurer;

    /**
     * 验证码拦截器配置
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    /**
     * 手机号验证码配置
     */
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler);

        http
                //   .httpBasic()  //使用弹出框进行身份认证
                .apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(gwfSocialConfigurer)
                    .and()
                .apply(openIdAuthenticationSecurityConfig)
                    .and()
                .authorizeRequests()    //认证的request authentication
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX,
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        securityProperties.getBrowser().getSession().getSessionExpiredUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        "/user/regist","/social/signUp")
                .permitAll()
                .anyRequest()          //所有请求
                .authenticated()       //都需要认证
                .and()
                .csrf().disable();     //禁止跨站拦截
    }
}
