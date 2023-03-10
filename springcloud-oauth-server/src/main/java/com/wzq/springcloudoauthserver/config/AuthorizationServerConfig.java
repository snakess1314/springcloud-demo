package com.wzq.springcloudoauthserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * webSecurityConfig ????????????AuthenticationManager
     */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    /**
     * ?????????????????????????????? token ?????????????????????????????????
     */
    @Autowired
    private DataSource dataSource;

    /**
     * webSecurityConfig ???????????? userDetailsService
     */
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;
   /* @Autowired
    private JwtAccessTokenConverter tokenConverter;*/
    @Autowired
    private DefaultTokenServices defaultTokenServices;
    /**
     * ??? oauth_client_details ??????????????????
     *
     * @return ClientDetailsService
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * ???????????????
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(new BaseClientDetailService());
        clients.jdbc(dataSource);
       /* clients.inMemory()
                .withClient("password") //client_id
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("order")
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token", "password", "implicit")
                .scopes("all")
                .redirectUris("http://www.baidu.com");*/


    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore);
        endpoints.userDetailsService(userDetailsService);
        endpoints.setClientDetailsService(clientDetails());
       // endpoints.accessTokenConverter(tokenConverter);
        endpoints.tokenGranter(tokenGranter(endpoints));
        endpoints.tokenServices(defaultTokenServices);
        endpoints.approvalStoreDisabled();
    }

    /**
     * ??????grant_type??????
     * @param endpoints
     * @return
     */
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> list = new ArrayList<>();
        // ????????????????????????
        if (authenticationManager != null) {
            list.add(new ResourceOwnerPasswordTokenGranter(authenticationManager,
                    endpoints.getTokenServices(),
                    endpoints.getClientDetailsService(),
                    endpoints.getOAuth2RequestFactory()));
        }
        //??????token?????????
        list.add(new RefreshTokenGranter
                (endpoints.getTokenServices(),
                        endpoints.getClientDetailsService(),
                        endpoints.getOAuth2RequestFactory()));
        //????????????????????????????????????
        list.add(new MobileCodeTokenGranter(
                authenticationManager,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));
        //??????????????????
        list.add(new AuthorizationCodeTokenGranter(
                endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));
        //???????????????
        list.add(new ImplicitTokenGranter(
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));

        //???????????????
        list.add(new ClientCredentialsTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),  endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(list);
    }
}
