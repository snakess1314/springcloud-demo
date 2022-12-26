package com.wzq.springcloudoauthserver.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {
/*    public static final String SIGN_KEY = "abc123";

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 对称秘钥，资源服务器使用该秘钥来验证
        jwtAccessTokenConverter.setSigningKey(SIGN_KEY);
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        // JWT令牌存储方案
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList( this.tokenConverter(),this.jwtTokenEnhancer()));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        defaultTokenServices.setAccessTokenValiditySeconds(60);
       // defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1)); // 1小时
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new CustomTokenEnhancer();
    }*/
}
