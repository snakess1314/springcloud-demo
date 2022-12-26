package com.wzq.springcloudmessageprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Value("${user.name}")
    private String name;
    @GetMapping("/echo/{message}")
    public String sendMessage(@PathVariable("message")String message){
        System.out.println("消息发送成功:"+message);
        return "ok:"+message;
    }
    @GetMapping("/sms/{message}")
    public String sendSms(@PathVariable("message")String message){
        System.out.println("消息发送成功sms:"+message);
        return "ok----sms:"+message;
    }
    @GetMapping("/test/myapp/show")
    public String test(){
        return "test is ok";
    }
}
