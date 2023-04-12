package com.by.service;

import com.by.base.BaseService;
import com.by.entity.House;

/**
 * @author xiaobai
 * @create 2023-04-06 21:11
 */
public interface HouseService extends BaseService<House> {
    void publish(Long id, Integer status);
}
