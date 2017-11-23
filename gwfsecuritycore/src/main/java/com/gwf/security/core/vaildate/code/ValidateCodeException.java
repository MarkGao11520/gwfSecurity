package com.gwf.security.core.vaildate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
public class ValidateCodeException extends AuthenticationException{



    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
