package com.by.dao;

import com.by.base.BaseDao;
import com.by.entity.Community;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-06 0:29
 */
public interface CommunityDao extends BaseDao<Community> {
    Community getById();

    List<Community> findAll();
}
