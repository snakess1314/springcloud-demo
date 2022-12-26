package com.wzq.springcloudoauthserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzq.springcloudoauthserver.mapper")
public class SpringcloudOauthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudOauthServerApplication.class, args);
    }

}
