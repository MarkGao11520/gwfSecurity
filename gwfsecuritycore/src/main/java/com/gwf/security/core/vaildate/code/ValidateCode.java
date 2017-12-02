package com.gwf.security.core.vaildate.code;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class ValidateCode implements Serializable{
    private static final long serialVersionUID = 7881659978168049598L;
    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code,LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired(){
        return this.expireTime.isAfter(expireTime);
    }
}
