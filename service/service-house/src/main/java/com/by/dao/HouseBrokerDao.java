package com.by.dao;

import com.by.base.BaseDao;
import com.by.entity.HouseBroker;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 8:46
 */
public interface HouseBrokerDao extends BaseDao<HouseBroker> {
    //根据房源id查询该房源的经纪人
    List<HouseBroker> findListByHouseId(Long houseId);
}
