package com.by.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.by.base.BaseController;
import com.by.entity.*;
import com.by.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-04-12 8:47
 */
@Controller
@RequestMapping("/house")
public class HouseBrokerController extends BaseController {

    private static final String SHOW_PAGE = "house/show";

    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private HouseUserService houseUserService;

    @RequestMapping("/{id}")
    public String toShowPage(@PathVariable Long id, Map map){
        House house = houseService.getById(id);
        Community community = communityService.getById(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
        List<HouseUser> houseUserList = houseUserService.findListByHouseId(id);
        List<HouseImage> houseImage1List = houseImageService.findList(id, 1);
        List<HouseImage> houseImage2List = houseImageService.findList(id, 2);
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseUserList",houseUserList);
        map.put("houseImage1List",houseImage1List);
        map.put("houseImage2List",houseImage2List);
        return SHOW_PAGE;
    }

}
