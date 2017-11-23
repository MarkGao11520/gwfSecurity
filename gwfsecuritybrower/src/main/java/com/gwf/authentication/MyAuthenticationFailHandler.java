package com.gwf.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwf.security.core.properties.LoginType;
import com.gwf.security.core.properties.SecurityProperties;
import com.gwf.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Component("myAuthenticationFailHandler")
@Slf4j
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler{

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        }else{
            super.onAuthenticationFailure(request, response, e);
        }
    }
}
