package com.wzq.springcloudoauthclient.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author wuzhaoqiang
 * @date 2021/10/16 20:21
 */
public class SecurityUtils {
    public static Authentication getAuthentication(){
       return SecurityContextHolder.getContext().getAuthentication();
    }
}
