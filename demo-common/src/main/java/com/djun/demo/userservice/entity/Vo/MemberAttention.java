package com.djun.demo.userservice.entity.Vo;

import com.djun.demo.userservice.entity.UmsMember;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

/**
 * created by DJun on 2019/7/22 9:34
 * desc:
 */
@Getter
@Setter
@Document
public class MemberAttention{
    @Id
    private Integer id;
    @Indexed
    private Long userId;
    @Indexed
    private Long followId;
}
