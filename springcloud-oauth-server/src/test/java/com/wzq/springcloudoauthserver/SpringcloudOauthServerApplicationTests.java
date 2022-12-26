package com.wzq.springcloudoauthserver;

import com.wzq.springcloudoauthserver.bean.UserInfo;
import com.wzq.springcloudoauthserver.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

@SpringBootTest
class SpringcloudOauthServerApplicationTests {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void contextLoads() {
        UserInfo snake = userInfoMapper.loadUserByUsername("snake");
        System.out.println(snake);
    }
    @Test
    public void fun1(){
        Properties properties = new Properties();
        //properties.setProperty("serverAddr",System.getProperty("serverAddr"));//从环境变量获取后放入Properties
        properties.setProperty("serverAddr","localhost:8848");
        properties.setProperty("username","nacos");
        properties.setProperty("namespace","ns-dev");
        properties.setProperty("password","nacos");

    }

}
