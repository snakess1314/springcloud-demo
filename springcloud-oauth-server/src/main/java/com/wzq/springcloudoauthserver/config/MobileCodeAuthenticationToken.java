package com.wzq.springcloudoauthserver.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author wuzhaoqiang
 * @date 2021/10/12 15:36
 */
public class MobileCodeAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 520L;
    /**
     * 主体信息
     *
     * @return
     */
    private final Object principal;

    /**
     * 未授权的
     *
     * @param
     * @param mobile
     */
    public MobileCodeAuthenticationToken(String mobile,String code) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }

    /**
     * 构建已经授权的 SmsCodeAuthenticationToken
     */
    public MobileCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
