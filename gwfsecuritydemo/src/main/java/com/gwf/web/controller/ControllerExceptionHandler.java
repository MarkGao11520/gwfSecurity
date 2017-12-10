package com.gwf.web.controller;

import com.gwf.exception.UserNotExistException;
import com.gwf.security.core.support.Result;
import com.gwf.security.core.support.ResultCode;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/10/3.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
        Map<String,Object> map = new HashedMap();
        map.put("id",ex.getId());
        map.put("message",ex.getMessage());
        return map;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleAuthenticationException(AuthenticationException e){
        Result result = new Result();
        result.setCode(ResultCode.UNAUTHORIZED).setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleAccessDeniedException(AccessDeniedException e){
        Result result = new Result();
        result.setCode(ResultCode.UNAUTHORIZED).setMessage(e.getMessage());
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
    public Map<String,Object> handleOtherException(Exception e){
////        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        System.out.println(handlerMethod.getBean());
        Map<String,Object> map = new HashedMap();
        map.put("message",e.getMessage());
        return map;
    }
}
