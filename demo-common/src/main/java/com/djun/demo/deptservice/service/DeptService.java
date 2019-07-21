package com.djun.demo.deptservice.service;

import com.djun.demo.deptservice.entity.Dept;

import java.util.List;

/**
 * created by DJun on 2019/6/20
 */
public interface DeptService {
     boolean add(Dept modelName);
     List<Dept> get(Long id);
     List<Dept> list();
     boolean delete(Long id);
}
