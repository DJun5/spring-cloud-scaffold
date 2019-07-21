package com.djun.demo.deptservice;

import com.djun.demo.common.result.CommonResult;
import com.djun.demo.deptservice.service.DeptClientService;
import com.djun.demo.deptservice.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 当此服务不起作用时，回复以下内容给客户端
 */
@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory<DeptClientService> {

    private final static String ERROR_MSG="服务暂不可用，";

    @Override
    public DeptClientService create(Throwable cause) {
        return new DeptClientService() {
            @Override
            public CommonResult getAll() {
                return CommonResult.failed(ERROR_MSG+"查询失败！");
            }

            @Override
            public CommonResult add(Dept model) {
                return CommonResult.failed(ERROR_MSG+"添加失败！");
            }

            @Override
            public CommonResult get(Long id) {
                return CommonResult.failed(ERROR_MSG+"id:"+id+" 查询失败！");
            }

            @Override
            public CommonResult delete(Long id) {
                return CommonResult.failed(ERROR_MSG+"id:"+id+" 删除失败！");
            }
        };


    }
}
