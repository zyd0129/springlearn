package com.zyd.learn.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;

import java.util.Arrays;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    TokenStore tokenStore;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private TokenEnhancer accessTokenConvertor;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")//token_key公开
                .checkTokenAccess("permitAll()")//check_token公开
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("client1"))
                .resourceIds("rs1")
                .authorizedGrantTypes("authorization_code", "password","refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");

    }

    @Bean
    AuthorizationCodeServices authorizationCodeServicesBean() {
        return  new InMemoryAuthorizationCodeServices();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) //密码模式需要
                .authorizationCodeServices(authorizationCodeServices) // code模式需要
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 令牌管理模式
     * @return
     */

    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setSupportRefreshToken(true);

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConvertor));
        defaultTokenServices.setTokenEnhancer(enhancerChain);

        defaultTokenServices.setRefreshTokenValiditySeconds(10000);
        defaultTokenServices.setAccessTokenValiditySeconds(1000);
        return defaultTokenServices;
    }
}
