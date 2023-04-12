package com.by.dao;

import com.by.base.BaseDao;
import com.by.entity.HouseUser;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 9:21
 */
public interface HouseUserDao extends BaseDao<HouseUser> {
    List<HouseUser> findListByHouseId(Long houseId);
}
