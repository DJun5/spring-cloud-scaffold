package com.djun.demo.controller;


import com.djun.demo.dept.entity.Dept;
import com.djun.demo.dept.service.DeptClientService;
import com.djun.demo.common.JSONResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * created by DJun on 2019/6/22
 */

@RestController
public class DeptController_Consumer {

    @Resource
    private DeptClientService service;

    @GetMapping("/test")
    public String test(){
        return "HELLO,WORLD！";
    }

    @GetMapping("/consumer/dept/list")
    public JSONResult getAll(){
        return JSONResult.ok(this.service.getAll());
    }

    @PostMapping("/consumer/dept/add")
    public JSONResult add( @RequestBody Dept model){
        return JSONResult.ok( this.service.add(model));
    }

        @GetMapping("/consumer/dept/findById/{id}")
    public JSONResult get(@PathVariable("id") Long id){
        return JSONResult.ok(this.service.get(id));
    }

    @PostMapping("/consumer/dept/delete/{id}")
    public JSONResult delete(@PathVariable("id") Long id){
        return JSONResult.ok( this.service.delete(id));
    }

}
