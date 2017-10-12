package com.gwf.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by gaowenfeng on 2017/10/6.
 * 过滤器，缺点，只能处理请求和相应，无法获取handler相关信息
 */
@Slf4j
// @Component   springboot注册过滤器方法1
public class TimeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("time filter 耗时:"+(new Date().getTime()-start));
        log.info("time filter finish");

    }

    @Override
    public void destroy() {
        log.info("time filter destory");

    }
}
