package com.djun.demo.serviceImpl;

import com.djun.demo.common.redis.RedisService;
import com.djun.demo.common.result.CommonResult;
import com.djun.demo.common.utils.EncryptUtil;
import com.djun.demo.mapper.UmsMemberMapper;
import com.djun.demo.userservice.entity.UmsMember;
import com.djun.demo.userservice.entity.UmsMemberExample;
import com.djun.demo.userservice.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * created by DJun on 2019/7/20 14:08
 * desc:
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Resource
    private UmsMemberMapper memberMapper;

    @Resource
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    private UmsMemberExample memberModel;
    @Value("${password.key}")
    private String pwd ;

    private EncryptUtil encryptUtil = new EncryptUtil();

    @Override
    public UmsMember getByUsername(String username) {
        memberModel = new UmsMemberExample();
        memberModel.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(memberModel);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommonResult register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            return CommonResult.failed("验证码错误");
        }
        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();

        example.createCriteria().andUsernameEqualTo(username);
        example.or(example.createCriteria().andMobileEqualTo(telephone));
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(umsMembers)) {
            return CommonResult.failed("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setMobile(telephone);
        umsMember.setPassword(encryptUtil.DESencode(password,pwd));
//        umsMember.setCreateTime(new Date());
//        umsMember.setStatus(1);
        //获取默认会员等级并设置
//        UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
//        levelExample.createCriteria().andDefaultStatusEqualTo(1);
//        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
//        if (!CollectionUtils.isEmpty(memberLevelList)) {
//            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
//        }
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
        return CommonResult.success(null,"注册成功");
    }

    @Override
    public CommonResult generateAuthCode(String telephone) {
        // 要修改字符串而不创建新的对象使用 StringBuilder 能够减少资源消耗
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
//        从redis获取验证码
//        System.out.println(redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone));
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString());
    }

    @Override
    public CommonResult updatePassword(String telephone, String password, String authCode) {
        memberModel = new UmsMemberExample();


        memberModel.createCriteria().andMobileEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(memberModel);
        if (CollectionUtils.isEmpty(memberList)) {
            return CommonResult.failed("该账号不存在");
        }
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            return CommonResult.failed("验证码错误");
        }
        UmsMember umsMember = memberList.get(0);
        // 密码应当使用单向加密

        umsMember.setPassword(encryptUtil.DESencode(password,pwd));
        memberMapper.updateByPrimaryKeySelective(umsMember);
        return CommonResult.success(null, "密码修改成功");
    }

    /**
     * Spring-Security
     */
    @Override
    public UmsMember getCurrentMember() {
//        SecurityContext ctx = SecurityContextHolder.getContext();
//        Authentication auth = ctx.getAuthentication();
//        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
//        return memberDetails.getUmsMember();
        return null;
    }

    // 验证验证码
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }

}
