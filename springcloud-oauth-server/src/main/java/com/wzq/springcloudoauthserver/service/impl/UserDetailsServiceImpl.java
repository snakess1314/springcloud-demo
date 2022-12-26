package com.wzq.springcloudoauthserver.service.impl;


import com.wzq.springcloudoauthserver.bean.UserInfo;
import com.wzq.springcloudoauthserver.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      UserInfo myUser= userInfoMapper.loadUserByUsername(s);
        if (myUser!=null) {
            return myUser;
        } else {
            throw new UsernameNotFoundException("账户没有找到");
        }

    }
    public UserDetails loadUserByMobile(String mobile){
        UserInfo myUser= userInfoMapper.loadUserByMobile(mobile);
        if (myUser!=null) {
            return myUser;
        } else {
            throw new UsernameNotFoundException("账户没有找到");
        }
    }
}
