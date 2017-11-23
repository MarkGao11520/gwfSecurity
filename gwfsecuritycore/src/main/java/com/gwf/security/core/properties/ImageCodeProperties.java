package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/11/23.
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;
    private String url;
}
