package com.wzq.springclouduserconsumer.service;

import java.util.Map;

public interface IOrderService {
    public String createOrder(Long id);

    Map<String, Object> getMapInfo();
}
