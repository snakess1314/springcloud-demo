package com.wzq.springclouduserprovider.service;

import com.wzq.springclouduserprovider.dao.BasicUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public BasicUser getUserById(Long id) {
        BasicUser basicUser = new BasicUser();
        basicUser.setId(id);
        basicUser.setName("张无忌");
        basicUser.setAge(28);
        basicUser.setMobilePhone("13111222734");
        return basicUser;
    }
}
