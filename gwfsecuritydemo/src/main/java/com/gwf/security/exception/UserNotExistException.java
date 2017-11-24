package com.gwf.security.exception;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/10/4.
 */
@Data
public class UserNotExistException extends RuntimeException{
    String id;
    public UserNotExistException(String id,String message){
        super(message);
        this.id = id;
    }
}
