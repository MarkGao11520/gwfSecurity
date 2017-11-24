package com.gwf.security.core.vaildate.code;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired(){
        return this.expireTime.isAfter(expireTime);
    }
}
