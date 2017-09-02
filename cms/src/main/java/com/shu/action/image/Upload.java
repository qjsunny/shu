package com.shu.action.image;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shu.action.image.UploadUtil;
import com.shu.action.image.Cut;
import com.shu.db.model.enterprise.Enterprise;
import com.shu.services.enterprise.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by james on 2017/5/8.
 */
@Controller
@RequestMapping(value = "/image")
public class Upload {

    @Autowired
    EnterpriseService enterpriseService;

    @RequestMapping(value = "/advertisement/{id}", method = RequestMethod.GET,  produces = "text/html;charset=UTF-8")
    public String uploadAdvertisement(@PathVariable("id") String id, Model model) {
        Enterprise enterprise = enterpriseService.getEnterpriseById(id);
        String url = enterprise.getAdvertisement();
        model.addAttribute("url", url);
        return "/image/advertisement";
    }

    @RequestMapping(value = "/certificate/{id}", method = RequestMethod.GET,  produces = "text/html;charset=UTF-8")
    public String uploadCertificate(@PathVariable("id") String id, Model model) {
        Enterprise enterprise = enterpriseService.getEnterpriseById(id);
        String url = enterprise.getCertificate();
        model.addAttribute("url", url);
        return "/image/certificate";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadCropper(
            String id, String uploadType,
            @RequestParam(value = "avatar_file", required = false)MultipartFile avatar_file,
            String avatar_src, String avatar_data, HttpServletRequest request) {
        System.out.println("==========Start=============");
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String resourcePath = "/upload/image/";
        //判断文件的MIME Type
        String type = avatar_file.getContentType();
        if (type == null || !UploadUtil.allowUpload(type))
            return JSON.toJSONString(new Result(null, "不支持的文件类型，仅支持图片！"));
        System.out.println("file type:" + type);
        //String fileName = UploadUtil.rename(avatar_file.getOriginalFilename());

        String fileName = id;
        if ("certificate".equals(uploadType))
            fileName = fileName + "cer.jpg";
        else if ("advertisement".equals(uploadType))
            fileName = fileName + "adv.jpg";

        int end = fileName.lastIndexOf(".");
        String saveName = fileName.substring(0,end);
        try {
            File dir = new File(realPath + resourcePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir,saveName+"_src.jpg");
            avatar_file.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(new Result(null, "上传失败，出现异常："+e.getMessage()));
        }
        String srcImagePath = realPath + resourcePath + saveName;
        JSONObject joData = (JSONObject) JSONObject.parse(avatar_data);
        // 用户经过剪辑后的图片的大小
        // 用户经过剪辑后的图片的大小
        float x = joData.getFloatValue("x");
        float y = joData.getFloatValue("y");
        float w =  joData.getFloatValue("width");
        float h =  joData.getFloatValue("height");
        float r = joData.getFloatValue("rotate");
        //这里开始截取操作
        System.out.println("==========imageCutStart=============");
        Cut.cutAndRotateImage(srcImagePath, (int)x,(int) y,(int) w,(int) h,(int) r);

        String nowImagePath = resourcePath + saveName + "_cut.jpg";
        Enterprise enterprise = enterpriseService.getEnterpriseById(id);
        if ("certificate".equals(uploadType))
            enterprise.setCertificate(nowImagePath);
        else if ("advertisement".equals(uploadType))
            enterprise.setAdvertisement(nowImagePath);
        enterpriseService.modifyEnterprise(enterprise);

        System.out.println("==========imageCutEnd=============");
        return  JSON.toJSONString(new Result(request.getSession().getServletContext().getContextPath()+resourcePath+saveName+"_cut.jpg","上传成功!"));
    }
}
