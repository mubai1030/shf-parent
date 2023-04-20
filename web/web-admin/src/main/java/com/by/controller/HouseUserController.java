package com.by.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.by.base.BaseController;
import com.by.entity.Admin;
import com.by.entity.House;
import com.by.entity.HouseUser;
import com.by.service.AdminService;
import com.by.service.HouseUserService;
import org.springframework.beans.BeanUtils;
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
 * @create 2023-04-20 1:04
 */
@Controller
@RequestMapping(value = "/houseUser")
public class HouseUserController extends BaseController {

    private static final String PAGE_CREATE = "houseUser/create";
    private static final String PAGE_EDIT = "houseUser/edit";
    private static final String LIST_ACTION = "redirect:/house/";;
    @Reference
    private HouseUserService houseUserService;
    @Reference
    private AdminService adminService;


    //进入添加页面
    @GetMapping("/create")
    public String create(Map map,HouseUser houseUser){
        map.put("houseUser",houseUser);
        return PAGE_CREATE;
    }

    //保存添加
    @PostMapping("/save")
    public String save(HouseUser houseUser, HttpServletRequest request){
        houseUserService.insert(houseUser);
        return this.successPage(this.MESSAGE_SUCCESS, request);
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        HouseUser houseUser = houseUserService.getById(id);
        model.addAttribute("houseUser",houseUser);
        return PAGE_EDIT;
    }


    @PostMapping(value="/update/{id}")
    public String update(ModelMap model, @PathVariable Long id, HouseUser houseUser, HttpServletRequest request) {
        HouseUser currentHouseUser = houseUserService.getById(id);
        BeanUtils.copyProperties(houseUser, currentHouseUser);

        houseUserService.update(currentHouseUser);
        return this.successPage(this.MESSAGE_SUCCESS, request);
    }

    @GetMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id) {
        houseUserService.delete(id);
        return LIST_ACTION + houseId;
    }

}
