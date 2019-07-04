package com.djun.demo.deptservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * created by DJun on 2019/6/19
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data                   // getter and setter
@Accessors(chain = true)  // 链式访问风格
public class Dept implements Serializable {
    private long deptno;
    private String  dname;
    private String  db_source;
}
