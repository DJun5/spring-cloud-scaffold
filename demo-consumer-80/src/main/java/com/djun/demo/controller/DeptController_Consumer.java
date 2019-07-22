package com.djun.demo.controller;


import com.djun.demo.common.result.CommonResult;
import com.djun.demo.deptservice.entity.Dept;
import com.djun.demo.deptservice.service.DeptClientService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * created by DJun on 2019/6/22
 */

@RestController
public class DeptController_Consumer {

    @Resource
    private DeptClientService service;

    @GetMapping("/consumer/dept/list")
    public CommonResult getAll(){
        return CommonResult.success(this.service.getAll());
    }

    @PostMapping("/consumer/dept/add")
    public CommonResult add( @RequestBody Dept model){
        return CommonResult.success( this.service.add(model));
    }

        @GetMapping("/consumer/dept/findById/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return CommonResult.success(this.service.get(id));
    }

    @PostMapping("/consumer/dept/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return CommonResult.success( this.service.delete(id));
    }

}
