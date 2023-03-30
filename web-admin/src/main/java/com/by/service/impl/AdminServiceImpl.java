package com.by.service.impl;

import com.by.base.BaseDao;
import com.by.base.BaseServiceImpl;
import com.by.dao.AdminDao;
import com.by.entity.Admin;
import com.by.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

//    @Autowired
    @Resource
    private AdminDao adminDao;

    @Override
    protected BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

}