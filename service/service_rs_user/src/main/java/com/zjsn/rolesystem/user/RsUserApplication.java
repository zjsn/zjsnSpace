package com.zjsn.rolesystem.user;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.zjsn.rolesystem.user.*")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zjsn.rolesystem.user.*")
@SpringBootApplication
@EnableSwagger2Doc
public class RsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(RsUserApplication.class,args);
    }
}
