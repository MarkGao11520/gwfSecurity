package com.gwf.security.brower.validate.code.impl;

import com.gwf.security.core.vaildate.code.ValidateCode;
import com.gwf.security.core.vaildate.code.ValidateCodeRepository;
import com.gwf.security.core.vaildate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by gaowenfeng on 2017/12/3.
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository{

    private static String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        sessionStrategy.setAttribute(request, getSessionKey(type), code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) sessionStrategy.getAttribute(request,getSessionKey(type));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        sessionStrategy.removeAttribute(request,getSessionKey(type));
    }

    /**
     * 构建验证码放入session时的key
     * @return
     */
    private String getSessionKey(ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }
}
