package com.djun.demo.dept.service;

import com.djun.demo.dept.entity.Dept;
import com.djun.demo.dept.DeptClientServiceFallBackFactory;
import com.djun.demo.common.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * created by DJun on 2019/6/27
 */

@FeignClient(value = "MICROSERVICE-DEMO",fallbackFactory = DeptClientServiceFallBackFactory.class)

public interface DeptClientService {
    /**
     * http://MICROSERVICE-DEMO/接口URI
     */

    @GetMapping("/dept/list")
    JSONResult getAll();

    @PostMapping("/dept/add")
    JSONResult add(@RequestBody Dept model);

    @GetMapping("/dept/findById/{id}")
    JSONResult get(@PathVariable("id") Long id);

    @PostMapping("/dept/delete/{id}")
    JSONResult delete(@PathVariable("id") Long id);

}
