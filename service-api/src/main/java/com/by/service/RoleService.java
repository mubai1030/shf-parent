package com.by.service;

import com.by.base.BaseService;
import com.by.entity.Role;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-03-25 21:57
 */

public interface RoleService extends BaseService<Role> {

    List<Role> findAll();

}
