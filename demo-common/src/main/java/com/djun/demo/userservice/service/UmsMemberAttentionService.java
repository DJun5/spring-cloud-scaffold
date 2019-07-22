package com.djun.demo.userservice.service;

import com.djun.demo.userservice.entity.Vo.MemberAttention;

import java.util.List;

/**
 * created by DJun on 2019/7/21 18:08
 * desc: 用户关注：
 *       1、用户关注用户
 */
public interface UmsMemberAttentionService {
    /**
     * 添加关注
     */
    int add(MemberAttention memberAttention);

    /**
     * 取消关注
     */
    int delete(Long userId, Long followId);

    /**
     * 获取用户关注列表
     */
    List<MemberAttention> list(Long userId);
}
