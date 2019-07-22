package com.djun.demo.repository;

import com.djun.demo.userservice.entity.Vo.MemberAttention;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * created by DJun on 2019/7/21 18:01
 * desc: 用户关注用户
 */
public interface MemberAttentionRepository extends MongoRepository<MemberAttention,String> {

    MemberAttention findByUserIdAndFollowId(Long userId, Long followId);
    int deleteByUserIdAndFollowId(Long userId,Long followId);
    List<MemberAttention> findByUserId(Long userId);
}
