package com.by.dao;

import com.by.base.BaseDao;
import com.by.entity.HouseBroker;
import com.by.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-12 9:19
 */
public interface HouseImageDao extends BaseDao<HouseImage>  {
    List<HouseImage> findList(@Param("houseId") Long houseId, @Param("type") Integer type);
}
