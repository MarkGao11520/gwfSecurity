package com.gwf.security.brower.security;

import com.gwf.security.brower.session.GwfExpiredSessionStrategy;
import com.gwf.security.core.authentication.AbstractChannelSecurityConfig;
import com.gwf.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.gwf.security.core.properties.SecurityConstants;
import com.gwf.security.core.properties.SecurityProperties;
import com.gwf.security.core.vaildate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Configuration
public class BrowerSecurityConfig extends AbstractChannelSecurityConfig{


    /**
     * 自定义配置参数类
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 自定义从数据库获取用户信息类
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 验证码拦截器配置
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 手机号验证码配置
     */
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    /**
     * qq登录配置
     */
    @Autowired
    private SpringSocialConfigurer gwfSocialConfigurer;

    /**
     * 退出成功处理器
     */
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    /**
     * Session并发登录
     */
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    /**
     * Session超时
     */
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    /**
     * 数据库连接池
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 密码加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public PersistentTokenRepository persisentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
  //      tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http
           //   .httpBasic()  //使用弹出框进行身份认证
                .apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(gwfSocialConfigurer)
                    .and()
                .rememberMe()
                    .tokenRepository(persisentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy)
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)
                    .and()
                    .and()
                .logout()
                    .logoutUrl("/signOut")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .deleteCookies("JSESSIONID")
                    .and()
                .authorizeRequests()    //认证的request authentication
                    .antMatchers(
                            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX,
                            securityProperties.getBrowser().getLoginPage(),
                            securityProperties.getBrowser().getSignUpUrl(),
                            securityProperties.getBrowser().getSignOutUrl(),
                            securityProperties.getBrowser().getSession().getSessionExpiredUrl(),
                            securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                            "/user/regist")
                    .permitAll()
                    .anyRequest()          //所有请求
                    .authenticated()       //都需要认证
                .and()
                .csrf().disable();     //禁止跨站拦截
    }
}
