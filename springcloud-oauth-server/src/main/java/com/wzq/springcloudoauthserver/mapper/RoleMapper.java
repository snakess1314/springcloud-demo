package com.wzq.springcloudoauthserver.mapper;

import com.wzq.springcloudoauthserver.bean.Role;

public interface RoleMapper {
    Role selectRoleByUserId(String id);
}
