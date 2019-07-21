package com.djun.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by DJun on 2019/6/23
 */
//@RestController
public class EurekaDiscoveryController {
//    // 此处导包易错，选择最长的那个包
//    @Resource
//    private DiscoveryClient client;
//
//    @GetMapping("/discovery")
//    public Object discovery(){
//        List<String> eurekaServiceList = client.getServices();
//        System.out.println("eurekaServiceList: "+eurekaServiceList);
//
//        List<ServiceInstance> srvList = client.getInstances("MICROSERVICE-DEMO");
//        for (ServiceInstance element : srvList){
//            System.out.println("ServiceId:"+element.getServiceId()+",HOST:"+element.getHost()
//                    +",PORT:"+element.getPort()+",URI:"+element.getUri());
//        }
//        return this.client;
//    }

}
