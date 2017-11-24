package com.gwf.security.brower.security;

import com.gwf.security.brower.support.SimpleResponse;
import com.gwf.security.core.properties.SecurityConstants;
import com.gwf.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@RestController
@Slf4j
public class BrowerSecurityController {

    @Autowired
    private SecurityProperties securityProperties;

    private RequestCache requestCache = new HttpSessionRequestCache();  //把请求的缓存缓存起来

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();



    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);

        if(savedRequest!=null){
            String target = savedRequest.getRedirectUrl();  //引发跳转请求的url
            log.info("引发跳转的请求的url:"+target);
            if(StringUtils.endsWithIgnoreCase(target,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
    }
}
