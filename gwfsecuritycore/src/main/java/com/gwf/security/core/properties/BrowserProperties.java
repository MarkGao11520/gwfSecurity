package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Data
public class BrowserProperties {
    private String loginPage = "/static/my-signIn.html";

    private LoginType loginType = LoginType.JSON;
}
