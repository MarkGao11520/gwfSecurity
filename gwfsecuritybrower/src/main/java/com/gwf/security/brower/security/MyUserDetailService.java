package com.gwf.security.brower.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名："+username);
        //根据用户查找用户信息
        //根据查找的用户的信息判读用户是否被冻结
        String password = passwordEncoder.encode("123456");
        //System.out.println(password);
        //log.info("登录用户密码是：",password);
        User user = new User(
                username,
                password,
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
