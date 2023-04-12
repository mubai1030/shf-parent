package com.by.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.by.base.BaseService;
import com.by.entity.HouseUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 9:53
 */
public interface HouseUserService extends BaseService<HouseUser> {
    List<HouseUser> findListByHouseId(Long id);
}
