package com.djun.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * created by DJun on 2019/6/20
 */
@SpringBootApplication
// 服务启动之后自动注册进入Eureka
@EnableEurekaClient
// 服务发现
@EnableDiscoveryClient
// Hystrix断路器
@EnableCircuitBreaker
public class DemoProvider8003_App {
    // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
    public static void main(String[] args) {
        SpringApplication.run(DemoProvider8003_App.class,args);
    }
}
