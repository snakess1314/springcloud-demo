package com.wzq.springclouduserprovider.service;

import com.wzq.springclouduserprovider.dao.BasicUser;

public interface IUserService {
    public BasicUser getUserById(Long id);
}
