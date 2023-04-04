package com.by.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.by.base.BaseController;
import com.by.result.Result;
import com.by.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-04-02 0:14
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Reference//远程调用
    DictService dictService;

    //展示数据字典页面
    @GetMapping
    public String index(){
        return "dict/index";
    }


    //获取数据字典中数据
    @GetMapping(value = "/findZnodes")
    @ResponseBody
    public Result findByParentId(@RequestParam(value = "id", defaultValue = "0") Long id) {
        List<Map<String,Object>> zNodes = dictService.findZnodes(id);
        return Result.ok(zNodes);
    }


}
