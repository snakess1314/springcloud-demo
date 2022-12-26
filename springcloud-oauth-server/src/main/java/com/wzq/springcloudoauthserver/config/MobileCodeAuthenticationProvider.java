package com.wzq.springcloudoauthserver.config;

import com.wzq.springcloudoauthserver.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 具体手机短信验证
 * @author wuzhaoqiang
 * @date 2021/10/12 15:40
 */
@Component
public class MobileCodeAuthenticationProvider implements AuthenticationProvider {


    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * 是否隐藏用户未发现异常，默认为true,为true返回的异常信息为BadCredentialsException
     */
    private boolean hideUserNotFoundExceptions = true;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String grant_type = getRequest().getParameter("grant_type");
        //代码逻辑处理处会循环调用provider 直到遇到能处理的provider类，典型的责任链模式
        if(!"mobile".equals(grant_type)){
            return null;
        }
        String mobile = (String) authentication.getPrincipal();

        if (mobile == null) {
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Missing mobile"));
        }
        String code = (String) authentication.getCredentials();
        code="9527";
        if (code == null) {
            System.out.println("缺失code参数");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Missing code"));
        }
        String cacheCode = "9527";
        if (cacheCode == null || !cacheCode.equals(code)) {
            System.out.println("短信验证码错误");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Invalid code"));
        }
        //清除redis中的短信验证码
        //stringRedisTemplate.delete(RedisConstant.SMS_CODE_PREFIX + mobile);
        UserDetails user;
        try {
            user = userDetailsServiceImpl.loadUserByMobile(mobile);
        } catch (UsernameNotFoundException var6) {
            System.out.println("手机号:" + mobile + "未查到用户信息");
            if (this.hideUserNotFoundExceptions) {
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
            throw var6;
        }
        check(user);
        MobileCodeAuthenticationToken authenticationToken = new MobileCodeAuthenticationToken(user, user.getAuthorities());
        authenticationToken.setDetails(authenticationToken.getDetails());
        return authenticationToken;
    }


    /**
     * ProviderManager 选择具体Provider时根据此方法判断
     * 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MobileCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * 账号禁用、锁定、超时校验
     *
     * @param user
     */
    private void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
        } else if (!user.isEnabled()) {
            throw new DisabledException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
        }
    }
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes ra= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  ra.getRequest();
        return request;
    }

}
