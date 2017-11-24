package com.gwf.security.core.vaildate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
public interface ValidateCodeGenerator {
     ValidateCode generate(ServletWebRequest request);
}
