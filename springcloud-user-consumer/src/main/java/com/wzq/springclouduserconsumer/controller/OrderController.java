package com.wzq.springclouduserconsumer.controller;

import com.wzq.springclouduserconsumer.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private IOrderService iOrderService;
    @GetMapping("payOrder/{id}")
    public String payOrder(@PathVariable("id") Long id){
        System.out.println("调用服务A");
        return iOrderService.createOrder(id);
    }
    @GetMapping("/degrade/show")
    public Map<String, Object> degrade(){

       return iOrderService.getMapInfo();
    }
}
