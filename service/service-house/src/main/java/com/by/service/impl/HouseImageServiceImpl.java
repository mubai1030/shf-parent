package com.by.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.by.base.BaseDao;
import com.by.base.BaseServiceImpl;
import com.by.dao.HouseImageDao;
import com.by.entity.HouseImage;
import com.by.service.HouseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 9:56
 */
@Service(interfaceClass = HouseImageService.class)
@Transactional
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage>
                                    implements HouseImageService {
    @Autowired
    private HouseImageDao houseImageDao;


    @Override
    public List<HouseImage> findList(Long id, int i) {
        List<HouseImage> houseImageList = houseImageDao.findList(id, i);
        return houseImageList;
    }

    @Override
    protected BaseDao<HouseImage> getEntityDao() {
        return houseImageDao;
    }
}
