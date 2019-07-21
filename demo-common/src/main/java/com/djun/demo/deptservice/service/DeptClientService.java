package com.djun.demo.deptservice.service;

import com.djun.demo.common.result.CommonResult;
import com.djun.demo.deptservice.entity.Dept;
import com.djun.demo.deptservice.DeptClientServiceFallBackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * created by DJun on 2019/6/27
 */

@FeignClient(value = "DEPTSERVICE-PROVIDER-8001",fallbackFactory = DeptClientServiceFallBackFactory.class)

public interface DeptClientService {
    /**
     * http://MICROSERVICE-DEMO/接口URI
     */

    @GetMapping("/dept/list")
    CommonResult getAll();

    @PostMapping("/dept/add")
    CommonResult add(@RequestBody Dept model);

    @GetMapping("/dept/findById/{id}")
    CommonResult get(@PathVariable("id") Long id);

    @PostMapping("/dept/delete/{id}")
    CommonResult delete(@PathVariable("id") Long id);

}
