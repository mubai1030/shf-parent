package com.by.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.by.base.BaseDao;
import com.by.base.BaseService;
import com.by.base.BaseServiceImpl;
import com.by.dao.CommunityDao;
import com.by.dao.DictDao;
import com.by.entity.Community;
import com.by.service.CommunityService;
import com.by.util.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-04-06 0:13
 */
@Service(interfaceClass = CommunityService.class)
@Transactional
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Autowired
    private CommunityDao communityDao;
    @Autowired
    private DictDao dictDao;


    @Override
    protected BaseDao<Community> getEntityDao() {
        return communityDao;
    }

    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 2);

        PageHelper.startPage(pageNum,pageSize);
        Page<Community> page = getEntityDao().findPage(filters);

        for (Community community : page) {
            //处理关联字段名称数据
            community.setAreaName(dictDao.getNameById(community.getAreaId()));
            community.setPlateName(dictDao.getNameById(community.getPlateId()));
        }

        return new PageInfo(page , 10);
    }


    @Override
    public List<Community> findAll() {
        return communityDao.findAll();
    }
}
