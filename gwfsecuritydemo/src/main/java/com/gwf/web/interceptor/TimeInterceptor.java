package com.gwf.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by gaowenfeng on 2017/10/6.
 * 拦截器相对过滤器优势，方法第三个参数是handler
 * 缺点，没有办法拿到具体的参数列表以及返回值
 */
@Slf4j
// @Component //这只是第一步
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 控制器执行之前执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setAttribute("startTime",new Date().getTime());
        log.info(((HandlerMethod)o).getBean().getClass().getName());
        log.info(((HandlerMethod)o).getMethod().getName());
        log.info("preHandle");
        return true;  //return false 表示不向下继续执行
    }

    /**
     * 控制器正常执行之后执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long start = (long) httpServletRequest.getAttribute("startTime");
        log.info("time interceptor 耗时:"+(new Date().getTime()-start));
        log.info("postHandle");
    }

    /**
     * 控制器执行之后，无论正常或异常，都会执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception  //exception 只能获取到controllerAdvice没有处理过的
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
        long start = (long) httpServletRequest.getAttribute("startTime");
        log.info("time interceptor 耗时:"+(new Date().getTime()-start));
        log.info("ex is "+e);
    }
}
