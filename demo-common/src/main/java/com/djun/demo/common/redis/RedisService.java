package com.djun.demo.common.redis;

/**
 * created by DJun on 2019/7/19
 * desc:
 *  redis操作Service,
 *  对象和数组都以json形式进行存储
 */

public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

}
