package com.djun.demo.userservice.service;

import com.djun.demo.common.result.CommonResult;
import com.djun.demo.userservice.entity.UmsMember;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by DJun on 2019/7/20 14:04
 * desc: 用户管理
 */
public interface UmsMemberService {
    /**
     * 根据用户名获取用户
     */
    UmsMember getByUsername(String username);

    /**
     * 根据用户编号获取用户
     */
    UmsMember getById(Long id);

    /**
     * 用户注册
     */
    @Transactional
    CommonResult register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    CommonResult updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录用户
     */
    UmsMember getCurrentMember();

}
