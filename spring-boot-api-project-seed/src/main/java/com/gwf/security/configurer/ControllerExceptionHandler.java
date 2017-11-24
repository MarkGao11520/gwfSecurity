package com.gwf.security.configurer;

import com.gwf.security.core.AuthErrorEnum;
import com.gwf.security.core.Result;
import com.gwf.security.core.ResultCode;
import com.gwf.security.core.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by gaowenfeng on 2017/10/3.
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleServiceException(ServiceException e){
        Result result = new Result();
        result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleAuthenticationException(AuthenticationException e){
        Result result = new Result();
        if (e instanceof BadCredentialsException)
            result.setCode(ResultCode.UNAUTHORIZED).setMessage(AuthErrorEnum.LOGIN_PARAM_ERROR.getMessage());
        else
            result.setCode(ResultCode.UNAUTHORIZED).setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleAccessDeniedException(AccessDeniedException e){
        Result result = new Result();
        result.setCode(ResultCode.UNAUTHORIZED).setMessage(AuthErrorEnum.ACCESS_DENIED.getMessage());
        return result;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleNoHandlerFoundException(NoHandlerFoundException e){
        Result result = new Result();
        result.setCode(ResultCode.NOT_FOUND).setMessage("接口不存在");
        return result;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleOtherException(Exception e){
        Result result = new Result();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口内部错误，请联系管理员");
        log.error(e.getMessage());
        return result;
    }
}
