package com.by.service;

import com.by.base.BaseService;
import com.by.entity.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-03-25 21:57
 */

public interface RoleService extends BaseService<Role> {

    List<Role> findAll();

}
