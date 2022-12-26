package com.wzq.springclouduserconsumer.service.feign;

import com.wzq.springclouduserconsumer.dao.BasicUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(value = "nacos-user-provider" ,fallback = UserFeignServiceFallBack.class)
public interface UserFeignService {
    @GetMapping("/getUserInfo/{id}")
    public BasicUser getUserById(@PathVariable(value = "id") Long id);
    @GetMapping("/degrade/show")
    Map<String, Object> getFeignMapInf();
}
