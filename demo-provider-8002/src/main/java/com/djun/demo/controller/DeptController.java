package com.djun.demo.controller;

import com.djun.demo.deptservice.entity.Dept;
import com.djun.demo.service.DeptService;
import com.djun.demo.common.JSONResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by DJun on 2019/6/20
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService service;


    @PostMapping("/add")
    public JSONResult add( @RequestBody Dept model){
        return JSONResult.ok(service.add(model));
    }

    @GetMapping("/findById/{id}")
    // 切面的方式
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public JSONResult get(@PathVariable("id") Long id){
        List<Dept> dept ;
        dept = service.get(id);
        if (dept.size() == 0){
//            throw new RuntimeException("该ID:"+id+"，没有对应的信息");
            return JSONResult.errorMsg("该ID:"+id+"，没有对应的信息");
        }
        return JSONResult.ok(service.get(id));
    }

    @GetMapping("/list")
    public JSONResult getAll(){
        return JSONResult.ok(service.list());
    }

    @PostMapping("/delete/{id}")
    public JSONResult delete(@PathVariable("id") Long id){
        return JSONResult.ok(service.delete(id));
    }

    /**
     * 响应注解 @HystrixCommand
     */
    public JSONResult processHystrix_Get(Long id){
        return JSONResult.errorMsg("该ID："+id+"，没有对应的信息--->@HystrixCommand");
    }
}
