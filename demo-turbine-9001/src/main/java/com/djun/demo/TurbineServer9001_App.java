package com.djun.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;


/**
 * created by DJun on 2019/7/4
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
// 若有问题，将此注解单独提出来启动
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableTurbine
public class TurbineServer9001_App {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServer9001_App.class,args);
    }
}
