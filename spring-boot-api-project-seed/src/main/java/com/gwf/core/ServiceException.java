package com.gwf.core;


/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 */
public class ServiceException extends RuntimeException {

    private Integer id;

    private static final long serialVersionUID = 170887664300433239L;

    public ServiceException() {
    }

    public ServiceException(Integer id,String message) {
        super(message);
        this.id = id;
    }

    public ServiceException(String message) {
        new ServiceException(ResultCode.FAIL.code,message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
