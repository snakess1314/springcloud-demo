package com.wzq.springclouduserprovider.controller;

import com.wzq.springclouduserprovider.dao.BasicUser;
import com.wzq.springclouduserprovider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/getUserInfo/{id}")
    public BasicUser getUserById(@PathVariable(value = "id") Long id) {
        return iUserService.getUserById(id);
    }

    @GetMapping("/degrade/show")
    public Map user() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "000000");
        map.put("info", "调用成功");
        return map;
    }
    @GetMapping("/demo/show")
    public Map AnyMouseShow() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "000000");
        map.put("info", "匿名调用");
        return map;
    }
    @GetMapping("app/show")
    public Map AppShow() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "000000");
        map.put("info", "APP调用");
        return map;
    }
}
