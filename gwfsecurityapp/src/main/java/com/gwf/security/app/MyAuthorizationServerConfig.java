package com.gwf.security.app;

import ch.qos.logback.core.rolling.helper.TokenConverter;
import com.gwf.security.core.properties.OAuth2ClientProperties;
import com.gwf.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import sun.tools.jstat.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/12/3.
 * 资源服务器
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig
        extends AuthorizationServerConfigurerAdapter{

    /**
     * AuthenticationProvider管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用户详情服务
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 配置文件
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * JWTtoken转换器
     */
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 自定义JWTtoken信息
     */
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    /**
     * token存储（jwt，redis）
     */
    @Autowired
    private TokenStore tokenStore;




    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore);
        if(jwtAccessTokenConverter !=null && jwtTokenEnhancer != null){
            TokenEnhancerChain chain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            chain.setTokenEnhancers(enhancers);
            endpoints
                    .tokenEnhancer(chain)
                    .accessTokenConverter(jwtAccessTokenConverter);

        }
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if(ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())){   //配置服务器信息
            for(OAuth2ClientProperties config:securityProperties.getOauth2().getClients()){
                builder
                        .withClient(config.getClientId())
                        .secret(config.getClientSecret())
                        .authorizedGrantTypes("refresh_token","authorization_code","password")
                        .refreshTokenValiditySeconds(259200)
                        .accessTokenValiditySeconds(config.getAccessTokenValidateSeconds());
            }
        }


    }
}
