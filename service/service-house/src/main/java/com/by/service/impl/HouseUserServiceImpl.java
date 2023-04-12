package com.by.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.by.base.BaseDao;
import com.by.base.BaseService;
import com.by.base.BaseServiceImpl;
import com.by.dao.HouseDao;
import com.by.dao.HouseUserDao;
import com.by.entity.HouseUser;
import com.by.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 10:33
 */
@Service(interfaceClass = HouseUserService.class)
@Transactional
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserDao houseUserDao;

    @Override
    protected BaseDao<HouseUser> getEntityDao() {
        return houseUserDao;
    }

    @Override
    public List<HouseUser> findListByHouseId(Long id) {
        List<HouseUser> listByHouseId = houseUserDao.findListByHouseId(id);
        return listByHouseId;
    }
}
