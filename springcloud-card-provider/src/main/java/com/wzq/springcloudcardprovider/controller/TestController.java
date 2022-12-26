package com.wzq.springcloudcardprovider.controller;

import com.wzq.springcloudcardprovider.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController

public class TestController {

    @GetMapping("/show")
    public String getName(@Valid User user){
        return "name:"+user.getUsername();
    }
}
