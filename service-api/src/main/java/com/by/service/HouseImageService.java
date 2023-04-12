package com.by.service;

import com.by.base.BaseService;
import com.by.entity.HouseImage;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 9:54
 */
public interface HouseImageService extends BaseService<HouseImage> {
    List<HouseImage> findList(Long id, int i);
}
