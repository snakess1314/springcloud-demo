package com.wzq.springclouduserconsumer.service.feign;

import com.wzq.springclouduserconsumer.dao.BasicUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserFeignServiceFallBack implements UserFeignService{
    @Override
    public BasicUser getUserById(Long id) {
        System.out.println("服务器发生熔断");
        return null;
    }

    @Override
    public Map<String, Object> getFeignMapInf() {
        System.out.println("下游系统服务出现故障");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code","000023");
        hashMap.put("info","下游服务故障");
        return hashMap;
    }
}
