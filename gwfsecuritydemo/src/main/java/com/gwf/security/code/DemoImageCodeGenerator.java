package com.gwf.security.code;

import com.gwf.security.core.vaildate.code.ValidateCodeGenerator;
import com.gwf.security.core.vaildate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
