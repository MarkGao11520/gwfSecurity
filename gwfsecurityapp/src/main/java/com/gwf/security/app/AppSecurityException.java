package com.gwf.security.app;

/**
 * Created by gaowenfeng on 2017/12/10.
 */
public class AppSecurityException extends RuntimeException {

    private static final long serialVersionUID = 124579300337860584L;

    public AppSecurityException(String msg) {
        super(msg);
    }
}
