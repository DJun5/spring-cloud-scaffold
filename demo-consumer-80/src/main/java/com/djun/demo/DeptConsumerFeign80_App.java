package com.djun.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * created by DJun on 2019/6/22
 */
@SpringBootApplication
@EnableEurekaClient
// 开启feign服务
@EnableFeignClients(basePackages = {"com.djun.demo"})

public class DeptConsumerFeign80_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerFeign80_App.class,args);
    }
}
