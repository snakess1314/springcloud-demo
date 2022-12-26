package com.wzq.springclouduserconsumer.service;

import com.wzq.springclouduserconsumer.dao.BasicUser;
import com.wzq.springclouduserconsumer.service.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private UserFeignService userFeignService;
    @Override
    public String createOrder(Long id) {
        BasicUser userById = userFeignService.getUserById(id);
        if (userById!=null) {
            System.out.println("创建订单完成");
            return "ok";
        }else {
            return "error：发生熔断";
        }
    }

    @Override
    public Map<String, Object> getMapInfo() {
        return userFeignService.getFeignMapInf();
    }
}
