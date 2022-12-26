package com.wzq.springclouduserprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = {"com.wzq"})
@EnableDiscoveryClient
public class SpringcloudUserProviderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringcloudUserProviderApplication.class, args);
        String property = run.getEnvironment().getProperty("server.port");
        System.out.println("服务器:"+property);
    }
}
