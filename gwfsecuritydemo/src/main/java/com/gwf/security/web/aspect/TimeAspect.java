package com.gwf.security.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by gaowenfeng on 2017/10/6.
 * 缺点，拿不到http请求和响应
 */
@Aspect
// @Component
@Slf4j
public class TimeAspect {

    /**
     * 第一个*代表任何返回值
     * 第二个*代表任何方法
     * docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#aop-pointcuts
     */
    @Around("execution(* com.gwf.security.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        log.info("time aspect start");

        Object[] args = pjp.getArgs();
        for(Object arg:args)
            log.info("arg is "+arg);

        long start = new Date().getTime();
        Object object =  pjp.proceed();
        log.info("time aspect 耗时:"+(new Date().getTime()-start));
        log.info("time aspect finish");
        return object;
    }

}
