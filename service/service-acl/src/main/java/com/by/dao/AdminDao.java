package com.by.dao;

import com.by.base.BaseDao;
import com.by.entity.Admin;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-03-30 14:43
 */
public interface AdminDao extends BaseDao<Admin> {

    List<Admin> findAll();
}
