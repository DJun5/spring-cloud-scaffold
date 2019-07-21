package com.djun.demo.serviceImpl;

import com.djun.demo.mapper.DeptMapper;
import com.djun.demo.deptservice.entity.Dept;
import com.djun.demo.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by DJun on 2019/6/20
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper mapper;

    @Override
    public boolean add(Dept modelName) {
        return mapper.addDept(modelName);
    }

    @Override
    public List<Dept> get(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Dept> list() {
        return mapper.findAll();
    }

    @Override
    public boolean delete(Long id) {
        return mapper.delete(id);
    }
}
