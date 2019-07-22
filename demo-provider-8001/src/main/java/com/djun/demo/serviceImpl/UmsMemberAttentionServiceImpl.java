package com.djun.demo.serviceImpl;

import com.djun.demo.userservice.entity.Vo.MemberAttention;
import com.djun.demo.repository.MemberAttentionRepository;
import com.djun.demo.userservice.service.UmsMemberAttentionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by DJun on 2019/7/22 9:13
 * desc:
 */
@Service
public class UmsMemberAttentionServiceImpl implements UmsMemberAttentionService {
    @Resource
    private MemberAttentionRepository memberAttentionRepository;

    @Override
    public int add(MemberAttention memberAttention) {
        int count = 0;
        MemberAttention findAttention = memberAttentionRepository.findByUserIdAndFollowId(memberAttention.getUserId(), memberAttention.getFollowId());
        if (findAttention == null) {
            memberAttentionRepository.save(memberAttention);
            count = 1;
        }mongodb:
        return count;
    }

    @Override
    public int delete(Long userId, Long followId) {
        return memberAttentionRepository.deleteByUserIdAndFollowId(userId,followId);
    }

    @Override
    public List<MemberAttention> list(Long userId) {
        return memberAttentionRepository.findByUserId(userId);
    }
}
