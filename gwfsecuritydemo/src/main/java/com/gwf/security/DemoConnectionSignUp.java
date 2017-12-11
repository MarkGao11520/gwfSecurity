package com.gwf.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/11/30.
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp{
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息，默认创建用户并返回用户唯一标示
        return connection.getDisplayName();
    }
}
