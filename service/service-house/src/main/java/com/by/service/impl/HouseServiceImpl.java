package com.by.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.by.base.BaseDao;
import com.by.base.BaseServiceImpl;
import com.by.dao.HouseDao;
import com.by.entity.House;
import com.by.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaobai
 * @create 2023-04-06 21:16
 */
@Service(interfaceClass = HouseService.class)
@Transactional
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Override
    protected BaseDao<House> getEntityDao() {
        return houseDao;
    }
}
