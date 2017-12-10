package com.gwf.security.app.social.openid;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by gaowenfeng on 2017/12/9.
 */
@Data
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken{


    private static final long serialVersionUID = 2475870989068863485L;

    private final Object principal;
    private String providerId;

    public OpenIdAuthenticationToken(String openId,String providerId) {
        super(null);
        this.principal = openId;
        this.providerId = providerId;
    }

    public OpenIdAuthenticationToken(Object principal,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }


}
