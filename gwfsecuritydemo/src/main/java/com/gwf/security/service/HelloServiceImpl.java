package com.gwf.security.service;

import org.springframework.stereotype.Service;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHellp(String name) {
        return "hello "+name;
    }
}
