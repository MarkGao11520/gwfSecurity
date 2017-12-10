package com.gwf.security.app.social.openid;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gaowenfeng on 2017/12/9.
 */
@Data
public class OpenIdAuthenticationProvider implements AuthenticationProvider{

    private SocialUserDetailsService userDetailsService;

    private UsersConnectionRepository usersConnectionRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;

        Set<String> providerUserIds = new HashSet<>();

        providerUserIds.add((String) authentication.getPrincipal());

        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(authenticationToken.getProviderId(),providerUserIds);

        if(CollectionUtils.isEmpty(userIds) || 1 !=userIds.size()){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        String userId = userIds.iterator().next();
        UserDetails user = userDetailsService.loadUserByUserId(userId);

        if(null == user){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user,user.getAuthorities());

        authenticationResult.setDetails(authentication.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
