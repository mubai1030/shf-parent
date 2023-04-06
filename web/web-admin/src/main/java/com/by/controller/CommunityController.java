package com.by.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.by.base.BaseController;
import com.by.entity.Community;
import com.by.entity.Dict;
import com.by.service.CommunityService;
import com.by.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-04-06 0:05
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    private static final String PAGE_INDEX = "community/index";
    private final static String PAGE_CREATE = "community/create";
    public static final String LIST_ACTION = "redirect:/community";
    private static final String PAGE_EDIT = "community/edit";

    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;

    @PostMapping("/update")
    public String update(Community community,HttpServletRequest request) {
        communityService.update(community);
        return this.successPage(this.MESSAGE_SUCCESS, request);
    }




    @RequestMapping("/edit/{id}")
    public String toEditPage(@PathVariable Long id, Map map){
        Community community = communityService.getById(id);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("community",community);
        map.put("areaList",areaList);
        return PAGE_EDIT;
    }

/*
    @GetMapping("/delete/{id}")
    public String delete(Long id) {
        communityService.delete(id);
        return LIST_ACTION;
    }
*/

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        communityService.delete(id);
        return LIST_ACTION;
    }

    @PostMapping("/save")
    public String save(Community community,HttpServletRequest request) {
        communityService.insert(community);
        return this.successPage(this.MESSAGE_SUCCESS, request);
    }

    @GetMapping("/create")
    public String toCreatePage(Map map) {
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList",areaList);
        return PAGE_CREATE;
    }

    @RequestMapping
    public String index(HttpServletRequest request,Map map){
        //1、分页数据的查询
        Map<String, Object> filters = getFilters(request);//获取请求参数
        if (!(filters.containsKey("areaId"))){
            filters.put("areaId","");
        }
        if (!(filters.containsKey("plateId"))){
            filters.put("plateId","");
        }
        PageInfo<Community> page = communityService.findPage(filters);

        //2、获取下拉列表选区
        List<Dict> areaList = dictService.findListByDictCode("beijing");

        //3、返回请求参数，回显
        map.put("areaList",areaList);
        map.put("page",page);
        map.put("filters",filters);
        return PAGE_INDEX;
    }


}
