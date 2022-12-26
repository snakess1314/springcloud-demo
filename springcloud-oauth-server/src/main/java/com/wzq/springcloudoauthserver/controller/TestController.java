package com.wzq.springcloudoauthserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/show")
    public String getShow(){
        return "hello world";
    }
}
