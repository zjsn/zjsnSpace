package com.zjsn.person;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.zjsn.person.*")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zjsn.person.*")
@SpringBootApplication
@EnableSwagger2Doc
public class PersonApplication {
    public static void main(String[] args) {

        SpringApplication.run(PersonApplication.class,args);
    }
}
