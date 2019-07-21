package com.djun.demo.repository;

import com.djun.demo.userservice.entity.UmsMember;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * created by DJun on 2019/7/21 18:01
 * desc:用户关注用户
 */
public interface MemberAttentionMember extends MongoRepository<UmsMember,String> {

}
