package com.gwf.security.core.vaildate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by gaowenfeng on 2017/12/3.
 */
public interface ValidateCodeRepository {

    void save(ServletWebRequest request,ValidateCode code,ValidateCodeType type);

    ValidateCode get(ServletWebRequest request,ValidateCodeType type);

    void remove(ServletWebRequest request,ValidateCodeType type);
}
