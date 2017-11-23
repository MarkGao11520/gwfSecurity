package com.gwf.security;

import com.gwf.security.core.properties.SecurityProperties;
import com.gwf.security.core.vaildate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ValidateCodeFilter validateCodeFilter() throws ServletException {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        return validateCodeFilter;
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


        http
           //   .httpBasic()  //使用弹出框进行身份认证
                .addFilterBefore(validateCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()  //使用表单进行身份认证
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persisentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .authorizeRequests()    //认证的request authentication
//                  .antMatchers("/**").permitAll()
                    .antMatchers("/authentication/require",
                            securityProperties.getBrowser().getLoginPage(),
                            "/code/image").permitAll()
                    .anyRequest()          //所有请求
                    .authenticated()       //都需要认证
                .and()
                .csrf().disable();     //禁止跨站拦截
    }
}
