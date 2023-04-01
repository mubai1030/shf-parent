package com.by.dao;

import com.by.base.BaseDao;
import com.by.entity.Role;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-03-25 21:28
 */
public interface RoleDao extends BaseDao<Role> {
    //模块特有的方法，声明到子接口中，公共的方法声明到父接口中
    List<Role> findAll();
}
