package com.by.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.by.base.BaseController;
import com.by.entity.HouseImage;
import com.by.result.Result;
import com.by.service.HouseImageService;
import com.by.util.QiniuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.UUID;

/**
 * @author xiaobai
 * @create 2023-04-20 13:48
 */
@Controller
@RequestMapping("/houseImage")
public class HouseImageController extends BaseController {

    private static final String PAGE_UPLOAD_SHOW = "house/upload";
    private static final String LIST_ACTION = "redirect:/house/";

    @Reference
    private HouseImageService houseImageService;

    /*删除图片*/
    @GetMapping("/delete/{houseId}/{id}")
    public String delete(ModelMap model,
                         @PathVariable Long houseId,
                         @PathVariable Long id) {
        HouseImage houseImage = houseImageService.getById(id);
        houseImageService.delete(id);
        QiniuUtils.deleteFileFromQiniu(houseImage.getImageUrl());
        return LIST_ACTION + houseId;
    }

    /*上传图片*/
    //<input type="file" name="file" multiple=“multiple”> 支持上传多个文件，name的名称“file”是固定的
    @RequestMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile[] files,
                         @PathVariable("houseId") Long houseId,
                         @PathVariable("type") Integer type) throws Exception{//接收上传文件，再上传到七牛云上
        if (files != null && files.length>0){//至少上传一个文件
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
//                String originalFilename = file.getOriginalFilename();//原始文件名称
//                QiniuUtils.upload2Qiniu(bytes,originalFilename);//不能使用原始文件名，后上传的可能会覆盖
                String newFileName = UUID.randomUUID().toString();
                //将图片上传到七牛云
                QiniuUtils.upload2Qiniu(bytes,newFileName);
                String imageUrl = "http://rtdkzysin.hn-bkt.clouddn.com/"+newFileName;

                //将图片路径信息等保存到数据库
                HouseImage houseImage = new HouseImage();
                houseImage.setHouseId(houseId);
                houseImage.setImageName(newFileName);
                houseImage.setImageUrl(imageUrl);
                houseImage.setType(type);
                houseImageService.insert(houseImage);
            }
        }
        return Result.ok();
    }

    /*跳转到上传界面*/
    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(@PathVariable("houseId") Long houseId,
                             @PathVariable("type") Integer type, Map map){
        map.put("houseId",houseId);
        map.put("type",type);
        return PAGE_UPLOAD_SHOW;
    }

}
