package com.by.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.by.base.BaseController;
import com.by.entity.*;
import com.by.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-04-06 21:09
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

    @Reference
    private HouseService houseService;
    @Reference
    private DictService dictService;
    @Reference
    private CommunityService communityService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house";
    private final static String PAGE_INDEX = "house/index";
    private final static String PAGE_SHOW = "house/show";
    private final static String PAGE_CREATE = "house/create";
    private final static String PAGE_EDIT = "house/edit";


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
        return PAGE_SHOW;
    }

    @GetMapping("/publish/{id}/{status}")
    public String publish(@PathVariable Long id,@PathVariable Integer status){
        houseService.publish(id,status);
        return LIST_ACTION;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        houseService.delete(id);
        return LIST_ACTION;
    }

    @PostMapping(value="/update")
    public String update(House house, HttpServletRequest request) {
        houseService.update(house);
        return this.successPage(this.MESSAGE_SUCCESS, request);
    }

    @RequestMapping("/edit/{id}")
    public String edit(Map map, @PathVariable Long id){
        House house = houseService.getById(id);
        map.put("house",house);

        //2、获取下拉列表数据
        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");

        //3、回显数据
        map.put("communityList",communityList);
        map.put("houseTypeList",houseTypeList);
        map.put("floorList ",floorList );
        map.put("buildStructureList",buildStructureList);
        map.put("directionList",directionList);
        map.put("decorationList",decorationList);
        map.put("houseUseList",houseUseList);
        return PAGE_EDIT;
    }

    @PostMapping("/save")
    public String save(House house, HttpServletRequest request) {
        houseService.insert(house);
        return this.successPage(this.MESSAGE_SUCCESS, request);
    }

    @RequestMapping("/create")
    public String toCreatePage(Map map){
        //2、获取下拉列表数据
        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");

        //3、回显数据
        map.put("communityList",communityList);
        map.put("houseTypeList",houseTypeList);
        map.put("floorList ",floorList );
        map.put("buildStructureList",buildStructureList);
        map.put("directionList",directionList);
        map.put("decorationList",decorationList);
        map.put("houseUseList",houseUseList);
        return PAGE_CREATE;
    }

    @RequestMapping
    public String toIndexPage(HttpServletRequest request,Map map){
        //1、分页
        Map<String,Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);
        map.put("page",page);
        map.put("filters",filters);

        //2、获取下拉列表数据
        List<Community> communityList = communityService.findAll();
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        List<Dict> floorList = dictService.findListByDictCode("floor");
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        List<Dict> directionList = dictService.findListByDictCode("direction");
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");

        //3、回显数据
        map.put("communityList",communityList);
        map.put("houseTypeList",houseTypeList);
        map.put("floorList ",floorList );
        map.put("buildStructureList",buildStructureList);
        map.put("directionList",directionList);
        map.put("decorationList",decorationList);
        map.put("houseUseList",houseUseList);
        return PAGE_INDEX;
    }



}
