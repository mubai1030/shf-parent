package com.by.service.impl;

import com.by.base.BaseDao;
import com.by.base.BaseServiceImpl;
import com.by.dao.RoleDao;
import com.by.entity.Role;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    protected BaseDao<Role> getEntityDao() {
        return roleDao;
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

}