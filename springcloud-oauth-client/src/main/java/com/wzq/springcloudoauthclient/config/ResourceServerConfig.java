package com.wzq.springcloudoauthclient.config;



import com.wzq.springcloudoauthclient.handler.AuthenticationAccessDeniedHandler;
import com.wzq.springcloudoauthclient.handler.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * @Author Heartsuit
 * @Date 2021-01-08
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private DefaultTokenServices defaultTokenServices;
    @Autowired
    private MyInvocationSecurityMetadataSource metadataSource;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new FilterSecurityInterceptorPostProcessor(urlAccessDecisionManager,metadataSource));

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(RESOURCE_ID).stateless(true);
        resources.authenticationEntryPoint(myAuthenticationEntryPoint)
                .tokenServices(defaultTokenServices)
                .accessDeniedHandler(authenticationAccessDeniedHandler);
    }
}

