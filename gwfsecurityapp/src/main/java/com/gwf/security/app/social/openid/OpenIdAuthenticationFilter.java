package com.gwf.security.app.social.openid;

import com.gwf.security.core.properties.SecurityConstants;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/12/9.
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openIdParamter = SecurityConstants.DEFAULT_PARAMETER_NAME_OPENID;
    private String providerIdParamter = SecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDERID;
    private boolean postOnly = true;

    public OpenIdAuthenticationFilter(){
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID, HttpMethod.POST.name()));
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        if(postOnly && !HttpMethod.POST.name().equals(request.getMethod())){
            throw new AuthenticationServiceException("Authentication method not supported: "+request.getMethod());
        }

        String openid = obtainOpenId(request);
        String providerId = obtaionProviderId(request);

        if(null == openid)
            openid = "";
        if(null == providerId)
            providerId = "";

        openid = openid.trim();
        providerId = providerId.trim();

        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openid,providerId);

        setDetails(request,authRequest);
        
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtaionProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParamter);
    }

    private String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(openIdParamter);
    }
}
