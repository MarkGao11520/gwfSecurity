package com.gwf.security.web.controller;

import com.gwf.security.exception.UserNotExistException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
