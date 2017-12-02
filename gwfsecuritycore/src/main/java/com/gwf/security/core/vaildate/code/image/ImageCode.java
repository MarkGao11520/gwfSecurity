package com.gwf.security.core.vaildate.code.image;

import com.gwf.security.core.vaildate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class ImageCode extends ValidateCode {
    private static final long serialVersionUID = 3238462150433218914L;
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;

    }

}
