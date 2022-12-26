package com.wzq.springcloudoauthserver.mapper;

import com.wzq.springcloudoauthserver.bean.UserInfo;

public interface UserInfoMapper {
    UserInfo loadUserByUsername(String username);

    UserInfo loadUserByMobile(String mobile);
}
