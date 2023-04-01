package com.by.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.by.base.BaseDao;
import com.by.base.BaseServiceImpl;
import com.by.dao.AdminDao;
import com.by.entity.Admin;
import com.by.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service(interfaceClass = AdminService.class)
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    protected BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

}