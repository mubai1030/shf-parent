package com.by.service;

import com.by.base.BaseService;
import com.by.entity.Admin;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-03-30 15:19
 */
public interface AdminService extends BaseService<Admin> {
    List<Admin> findAll();

}
