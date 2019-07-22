package com.djun.demo.controller;

import com.djun.demo.common.rabbitmq.MsgProducer;
import com.djun.demo.common.result.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by DJun on 2019/7/22 13:25
 * desc: 测试RabbitMq
 */
@Api("消息队列")
@RestController
public class MsgController {
    @Resource
    private MsgProducer msgProducer;


    @ApiOperation("测试接口")
    @GetMapping("/msg")
    public CommonResult getMsg() {
        msgProducer.send();
        return CommonResult.success(null,"发送成功");
    }
}
