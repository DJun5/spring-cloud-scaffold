package com.djun.demo.controller;


import com.djun.demo.common.result.CommonResult;
import com.djun.demo.deptservice.entity.Dept;
import com.djun.demo.deptservice.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by DJun on 2019/6/20
 */
@Api(description = "部门接口")
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService service;

    @ApiOperation(value = "新增部门", produces = "application/json")
    @PostMapping("/add")
    public CommonResult add(@RequestBody Dept model) {
        return CommonResult.success(service.add(model));
    }

    @ApiOperation(value = "查询指定部门", notes = "通过id查询", produces = "application/json")
    @GetMapping("/findById/{id}")

    // 切面的方式
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public CommonResult get(@PathVariable("id") Long id) {
        List<Dept> dept;
        dept = service.get(id);
        if (dept.size() == 0) {
//            throw new RuntimeException("该ID:"+id+"，没有对应的信息");
            return CommonResult.failed("该ID:" + id + "，没有对应的信息");
        }
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查询全部部门", notes = "获取所有部门")
    @GetMapping("/list")
    public CommonResult getAll() {
        return CommonResult.success(service.list());
    }

    @ApiOperation(value = "删除部门", notes = "通过id删除")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        return CommonResult.success(service.delete(id));
    }

    /**
     * 响应注解 @HystrixCommand
     */
    public CommonResult processHystrix_Get(Long id) {
        return CommonResult.failed("该ID：" + id + "，没有对应的信息--->@HystrixCommand");
    }


}
