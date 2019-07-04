package com.djun.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * created by DJun on 2019/7/3
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulServer9527_App {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServer9527_App.class,args);
    }
}
