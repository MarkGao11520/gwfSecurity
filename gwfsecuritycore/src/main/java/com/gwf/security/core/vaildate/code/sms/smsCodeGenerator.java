package com.gwf.security.core.vaildate.code.sms;

import com.gwf.security.core.properties.SecurityProperties;
import com.gwf.security.core.vaildate.code.ValidateCode;
import com.gwf.security.core.vaildate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
@Component("smsValidateCodeGenerator")
public class smsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
