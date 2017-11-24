package com.gwf.security.core.vaildate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;


    /**
     * 创建验证码，根据验证码类型不同，调用不同的@{link ValidateProcessor}接口的实现
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));

    }



}
