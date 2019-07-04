package com.djun.demo.mapper;

import com.djun.demo.deptservice.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * created by DJun on 2019/6/20
 */
@Mapper
public interface DeptMapper {
    boolean addDept(Dept modelName);
    List<Dept> findById(Long id);
    List<Dept> findAll();
    boolean delete(Long id);
}
