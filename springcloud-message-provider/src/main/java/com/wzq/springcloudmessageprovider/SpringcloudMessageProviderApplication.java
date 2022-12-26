package com.wzq.springcloudmessageprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.wzq"})
@EnableDiscoveryClient
public class SpringcloudMessageProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudMessageProviderApplication.class, args);
    }

}
