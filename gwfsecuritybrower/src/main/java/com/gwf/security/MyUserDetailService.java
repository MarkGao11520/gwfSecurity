package com.gwf.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名："+username);
        //根据用户查找用户信息
        return new User(username,"123456",
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
