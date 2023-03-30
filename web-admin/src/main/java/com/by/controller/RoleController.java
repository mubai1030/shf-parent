package com.by.controller;

import com.by.base.BaseController;
import com.by.entity.Role;
import com.by.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author xiaobai
 * @create 2023-03-25 22:01
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final static String PAGE_INDEX = "role/index";
//    private final static String PAGE_SUCCESS = "common/successPage";
    private final static String PAGE_EDIT = "role/edit";
    private final static String ACTION_LIST = "redirect:/role";

    @Autowired
    private RoleService roleService;

    //删除角色信息
    @RequestMapping("/delete/{id}")
    public String update(@PathVariable("id") Long id){
        //调用RoleService中添加的方法
        roleService.delete(id);
        return ACTION_LIST;
    }


/*    //修改角色信息
    @RequestMapping("/update")
    public String update(Role role,Map map){
        //调用RoleService中添加的方法
        roleService.update(role);
        map.put("message","修改成功");
        return PAGE_SUCCESS;
    }*/

    //修改角色信息
    @RequestMapping("/update")
    public String update(Role role,HttpServletRequest request){
        //调用RoleService中添加的方法
        roleService.update(role);
        return this.successPage("修改成功",request);
    }

    //跳转修改角色信息页面
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Map map){
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

/*    //添加角色
    @RequestMapping("/save")
    public String save(Role role,Map map){
        //调用RoleService中添加的方法
        roleService.insert(role);
        map.put("message","添加成功");
        //重定向到查询所有角色的方法
        //return "redirect:/role"; //重定向到查询所有的页面
        //优化，去common下的成功页面
        return PAGE_SUCCESS;
    }*/
    //添加角色
    @RequestMapping("/save")
    public String save(Role role,HttpServletRequest request){
        //调用RoleService中添加的方法
        roleService.insert(role);
        return this.successPage("添加成功",request);
    }

    //跳转到添加角色页面
    @RequestMapping("/create")
    public String create(){
        return "role/create";
    }

/*    @RequestMapping
    public String index(Map map){
        //调用RoleService中获取所有的角色的方法
        List<Role> roleList = roleService.findAll();
        map.put("list",roleList);
        return "role/index";
    }*/

    /*
    * 分页查询
    *   根据条件进行查询
    *       roleName = ‘’
    *       pageName = 1    隐藏域
    *       pageSize = 10   隐藏域
    * */
    @RequestMapping
    public String index(HttpServletRequest request,Map map){
        Map<String, Object> filters = getFilters(request);
        //分页对象，将集合数据 pageName，pageSize，pages，total等
        PageInfo pageInfo = roleService.findPage(filters);
        map.put("page",pageInfo);
        map.put("filters",filters);
        return PAGE_INDEX;
    }


}
