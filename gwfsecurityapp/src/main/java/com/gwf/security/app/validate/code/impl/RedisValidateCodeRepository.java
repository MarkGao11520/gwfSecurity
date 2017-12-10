package com.gwf.security.app.validate.code.impl;

import com.gwf.security.core.vaildate.code.ValidateCode;
import com.gwf.security.core.vaildate.code.ValidateCodeException;
import com.gwf.security.core.vaildate.code.ValidateCodeRepository;
import com.gwf.security.core.vaildate.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaowenfeng on 2017/12/3.
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository{

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        redisTemplate.opsForValue().set(buildKey(request,type),code,30, TimeUnit.MINUTES);
    }



    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        Object value = redisTemplate.opsForValue().get(buildKey(request,type));
        if(value == null)
            return null;
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(buildKey(request,type));
    }

    private Object buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return "code:"+type.toString().toLowerCase()+":"+deviceId;
    }
}
