package com.by.service;

import com.by.base.BaseService;
import com.by.entity.HouseBroker;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 8:48
 */
public interface HouseBrokerService extends BaseService<HouseBroker> {
    List<HouseBroker> findListByHouseId(Long id);
}
