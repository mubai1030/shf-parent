package com.by.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-03-29 16:53
 */
public interface BaseDao<T> {

    /**
     * 保存一个实体
     *
     * @param t
     */
    Integer insert(T t);


    /**
     * 删除
     *
     * @param id 标识ID 可以是自增长ID，也可以是唯一标识。
     */
    void delete(Serializable id);

    /**
     * 更新一个实体
     *
     * @param t
     */
    Integer update(T t);

    /**
     * 通过一个标识ID 获取一个唯一实体
     *
     * @param id 标识ID 可以是自增长ID，也可以是唯一标识。
     * @return
     */
    T getById(Serializable id);

    Page<T> findPage(Map<String, Object> filters);
}
