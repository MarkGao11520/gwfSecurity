package com.gwf.security.core.vaildate.code;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired(){
        return this.expireTime.isAfter(expireTime);
    }
}
