package com.djun.demo.controller;

import com.djun.demo.common.result.CommonResult;
import com.djun.demo.userservice.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * created by DJun on 2019/7/20 14:58
 * desc:
 */
@Api(tags = "UmsMemberController", description = "用户登录注册修改管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {
    @Resource
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }


    @ApiOperation("注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        return memberService.register(username, password, telephone, authCode);
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
        return memberService.updatePassword(telephone,password,authCode);
    }
}
