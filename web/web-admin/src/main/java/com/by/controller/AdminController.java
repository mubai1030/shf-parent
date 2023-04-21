package com.by.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.by.base.BaseController;
import com.by.entity.Admin;
import com.by.result.Result;
import com.by.service.AdminService;
import com.by.util.QiniuUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value="/admin")
public class AdminController extends BaseController {

    //    @Autowired
    @Reference
    private AdminService adminService;

    private static final String PAGE_UPLOAD_SHOW = "admin/upload";
    private final static String LIST_ACTION = "redirect:/admin";

    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping("/upload/{id}")
    public String upload(@PathVariable Long id,
                                 @RequestParam(value = "file")MultipartFile file,
                         HttpServletRequest request) throws Exception{
        String newFileName = UUID.randomUUID().toString();
        QiniuUtils.upload2Qiniu(file.getBytes(),newFileName);;
        String imageUrl = "http://rtdkzysin.hn-bkt.clouddn.com/"+newFileName;
        Admin admin = new Admin();
        admin.setId(id);
        admin.setHeadUrl(imageUrl);
        adminService.update(admin);
        return this.successPage(this.MESSAGE_SUCCESS, request);
    }

    /*跳转到上传页面*/
    @RequestMapping("/uploadShow/{id}")
    public String uploadShow(@PathVariable("id") Long id,
                             Map map){
        map.put("id",id);
        return PAGE_UPLOAD_SHOW;
    }

    /**
     * 列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String,Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    /**
     * 进入新增页面
     * @return
     */
    @GetMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 保存新增
     * @param admin
     * @return
     */
    @PostMapping("/save")
    public String save(Admin admin) {
        //设置默认头像
        admin.setHeadUrl("http://47.93.148.192:8080/group1/M00/03/F0/rBHu8mHqbpSAU0jVAAAgiJmKg0o148.jpg");
        adminService.insert(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 进入编辑页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin",admin);
        return PAGE_EDIT;
    }

    /**
     * 保存更新
     * @param admin
     * @return
     */
    @PostMapping(value="/update")
    public String update(Admin admin) {

        adminService.update(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        return LIST_ACTION;
    }
}
