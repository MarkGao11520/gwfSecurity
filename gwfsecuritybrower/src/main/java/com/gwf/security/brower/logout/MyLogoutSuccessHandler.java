package com.gwf.security.brower.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwf.security.core.support.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/12/2.
 */
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private String signOutUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public MyLogoutSuccessHandler(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");

        if(StringUtils.isBlank(signOutUrl)){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResultGenerator.genSuccessResult("退出成功")));
        }else {
            response.sendRedirect(signOutUrl);
        }
    }
}
