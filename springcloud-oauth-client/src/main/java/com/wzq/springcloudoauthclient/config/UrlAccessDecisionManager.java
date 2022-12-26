package com.wzq.springcloudoauthclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author wuzhaoqiang
 * @date 2020/8/31 10:34
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    /**
     * 传过来的参数校验角色
     *
     * @param authentication
     * @param o
     * @param collection     从customfilter过滤过来的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        //用户所具有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //该资源需要的权限
        for (ConfigAttribute configAttribute : collection) {
            //该请求所需要的角色权限
            String needRole = configAttribute.getAttribute();
            //1.先判断匿名访问允许，认证了也可以访问
            if ("ROLE_ANONYMOUS".equals(needRole)) {
                return;
            }
            //2.判断只要登录就可以访问
            if ("ROLE_LOGIN".equals(needRole)) {
                //判断用户是不是匿名用户
                if (authentication instanceof AnonymousAuthenticationToken) {
                    System.out.println("匿名用户访问");
                    throw new BadCredentialsException("用户未登录");
                } else {
                    return;
                }

            }
            //当前用户所具有的权限
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }

        }
        //未登录
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException("用户未登录");
        } else {
            throw new AccessDeniedException("权限不足,无法访问");
        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
