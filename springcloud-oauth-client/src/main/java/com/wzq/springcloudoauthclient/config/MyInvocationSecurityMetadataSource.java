package com.wzq.springcloudoauthclient.config;

import com.wzq.springcloudoauthclient.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wuzhaoqiang
 * @date 2021/10/16 13:51
 */
@Component
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    // 这里的需要从DB加载
    private final Map<String, String> urlRoleMap = new HashMap<String, String>() {{
        put("/app/**", "ROLE_TEST2");
        put("/getUserInfo/**", "ROLE_ADMIN");
        put("/demo/**", "ROLE_ANONYMOUS");
    }};
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    TokenStore tokenStore;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        Authentication authentication = SecurityUtils.getAuthentication();

        if (authentication instanceof OAuth2Authentication) {
            Set<String> scope = ((OAuth2Authentication) authentication).getOAuth2Request().getScope();
            for (String sco : scope) {
                System.out.println("作用域:" + sco);
            }
        }
        FilterInvocation fi = (FilterInvocation) o;
        String url = fi.getRequestUrl();
        for (Map.Entry<String, String> entry : urlRoleMap.entrySet()) {
            if (antPathMatcher.match(entry.getKey(), url)) {
                return SecurityConfig.createList(entry.getValue());
            }
        }

        //  返回代码定义的默认配置
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
