package com.djun.demo.deptservice;

import com.djun.demo.deptservice.service.DeptClientService;
import com.djun.demo.deptservice.entity.Dept;
import com.djun.demo.common.JSONResult;
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
            public JSONResult getAll() {
                return JSONResult.errorMsg(ERROR_MSG+"查询失败！");
            }

            @Override
            public JSONResult add(Dept model) {
                return JSONResult.errorMap(ERROR_MSG+"添加失败！");
            }

            @Override
            public JSONResult get(Long id) {
                return JSONResult.errorMsg(ERROR_MSG+"id:"+id+" 查询失败！");
            }

            @Override
            public JSONResult delete(Long id) {
                return JSONResult.errorMsg(ERROR_MSG+"id:"+id+" 删除失败！");
            }
        };


    }
}
