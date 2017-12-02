package com.gwf.security.core.properties;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/10/12.
 */
@Data
public class BrowserProperties {

    private String signUpUrl = "my-signUp.html";

    private String signOutUrl = "";

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600; //默认一小时

    private SessionProperties session = new SessionProperties();

}
