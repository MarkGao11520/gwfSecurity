package com.gwf.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService,SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("表单登录用户名："+username);
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("社交登录userId："+userId);
        return buildUser(userId);
    }

    /**
     * 根据用户查找用户信息
     * 根据查找的用户的信息判读用户是否被冻结
     * @param userId
     * @return
     */
    private  SocialUserDetails buildUser(String userId){

        String password = passwordEncoder.encode("123456");
        log.info("登录用户密码是："+password);
        SocialUser user = new SocialUser(
                userId,
                password,
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
        return  user;
    }
}