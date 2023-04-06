package com.by.service;

import com.by.base.BaseService;
import com.by.entity.Community;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-06 0:11
 */
public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
