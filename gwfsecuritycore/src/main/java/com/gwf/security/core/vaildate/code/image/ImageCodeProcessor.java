package com.gwf.security.core.vaildate.code.image;

import com.gwf.security.core.vaildate.code.impl.AbstractValidateProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/11/24.
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateProcessor<ImageCode> {

    /**
     * 发送图片验证码,将其写入响应中
     * @param request
     * @param imageCode
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
