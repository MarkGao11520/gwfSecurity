package com.gwf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
           //   .httpBasic()  //使用弹出框进行身份认证
                .formLogin()  //使用表单进行身份认证
                .and()
                .authorizeRequests()   //认证的request
                .anyRequest()          //所有请求
                .authenticated();      //都需要认证
    }
}
