package com.shu.action.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
/*@RequestMapping("/UploadDemo")*/
public class UploadImgController {
	
	@RequestMapping(value = "/uploadHeadImage",method = RequestMethod.GET)
    public String uploadCropper(){
		return "cropper";
	}
	
	@RequestMapping(value = "/uploadHeadImage",method = RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
    public String uploadCropper(
    		@RequestParam(value = "avatar_file",required=false) MultipartFile avatar_file,
			 String avatar_src,String avatar_data, HttpServletRequest request) {
				System.out.println("==========Start=============");
				String realPath = request.getSession().getServletContext().getRealPath("/");
				String resourcePath = "/upload/image/";
		        //判断文件的MIMEtype
		        String type = avatar_file.getContentType();
		        if(type==null || !FileUploadUtil.allowUpload(type)) return  JSON.toJSONString(new Result(null,"不支持的文件类型，仅支持图片！"));
		        System.out.println("file type:"+type);
                String fileName = FileUploadUtil.rename(avatar_file.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0,end);
                try {
                	File dir = new File(realPath + resourcePath);
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    File file = new File(dir,saveName+"_src.jpg");
					avatar_file.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();  
		            return  JSON.toJSONString(new Result(null,"上传失败，出现异常："+e.getMessage()));
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
                ImgCut.cutAndRotateImage(srcImagePath, (int)x,(int) y,(int) w,(int) h,(int) r);
                System.out.println("==========imageCutEnd=============");            
            
        return  JSON.toJSONString(new Result(request.getSession().getServletContext().getContextPath()+resourcePath+saveName+"_cut.jpg","上传成功!"));
  }
       

}
	
	/*@RequestMapping(value = "/uploadHeadImage",method = RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
    public String uploadCropper(
    		@RequestParam(value = "avatar_file",required=false) MultipartFile avatar_file,
			 String avatar_src,String avatar_data, HttpServletRequest request,Model model
    ) throws Exception{
        System.out.println("==========Start=============");
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String resourcePath = "resources/uploadImages/";
        if(avatar_file!=null){
            if(FileUploadUtil.allowUpload(avatar_file.getContentType())){
                String fileName = FileUploadUtil.rename(avatar_file.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0,end);
                File dir = new File(realPath + resourcePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir,saveName+"_src.jpg");
                avatar_file.transferTo(file);
                String srcImagePath = realPath + resourcePath + saveName;
                JSONObject joData = (JSONObject) JSONObject.parse(avatar_data);
                // 用户经过剪辑后的图片的大小  
                // 用户经过剪辑后的图片的大小  
                float x = joData.getFloatValue("x");
                float y = joData.getFloatValue("y");
                float w = joData.getFloatValue("width");
                float h = joData.getFloatValue("height");
                float r = joData.getFloatValue("rotate");
                //这里开始截取操作
                System.out.println("==========imageCutStart=============");
                ImgCut.cutAndRotateImage(srcImagePath, (int)x,(int) y,(int) w,(int) h,(int) r);//.imgCut(srcImagePath, (int)x,(int)y,(int)w,(int)h);
                System.out.println("==========imageCutEnd=============");
                model.addAttribute("srcImagePath", srcImagePath);
                System.out.println(srcImagePath);
                //request.getSession().setAttribute("imgSrc",srcImagePath+"_src.jpg");//成功之后显示用
                //request.getSession().setAttribute("imgCut",srcImagePath+"_cut.jpg");//成功之后显示用
            }
        }
       
        return "cropper";
        
    }*/
	
	/*@RequestMapping(value = "/uploadHeadImage",method = RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
    public String uploadCropper(
    		@RequestParam(value = "avatar_file",required=false) MultipartFile avatar_file,
			 String avatar_src,String avatar_data, HttpServletRequest request
    ) throws Exception{
        System.out.println("==========Start=============");
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String resourcePath = "resources/uploadImages/";
        if(avatar_file!=null){
            if(FileUploadUtil.allowUpload(avatar_file.getContentType())){
                String fileName = FileUploadUtil.rename(avatar_file.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0,end);
                File dir = new File(realPath + resourcePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir,saveName+"_src.jpg");
                avatar_file.transferTo(file);
                String srcImagePath = realPath + resourcePath + saveName;
                JSONObject joData = (JSONObject) JSONObject.parse(avatar_data);
                // 用户经过剪辑后的图片的大小  
                // 用户经过剪辑后的图片的大小  
                float x = joData.getFloatValue("x");
                float y = joData.getFloatValue("y");
                float w =  joData.getFloatValue("width");
                float h =  joData.getFloatValue("height");
                //这里开始截取操作
                System.out.println("==========imageCutStart=============");
                ImgCut.imgCut(srcImagePath, (int)x,(int)y,(int)w,(int)h);
                System.out.println("==========imageCutEnd=============");
                //request.getSession().setAttribute("imgSrc",srcImagePath+"_src.jpg");//成功之后显示用
                //request.getSession().setAttribute("imgCut",srcImagePath+"_cut.jpg");//成功之后显示用
            }
        }
       
        return "success";
        
    }*/
	
	/*@RequestMapping(value = "/uploadHeadImage",method = RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody  
	 public String profile_imgCut(MultipartFile avatar_file,String avatar_src,String avatar_data, HttpServletRequest request, Model model) {
        String dir = "/upload/image/";
        String path = request.getSession().getServletContext().getRealPath(dir);  

        String name = avatar_file.getOriginalFilename();
        //判断文件的MIMEtype
        String type = avatar_file.getContentType();
        if(type==null || !type.toLowerCase().startsWith("image/")) return  JSON.toJSONString(new Result(null,"不支持的文件类型，仅支持图片！"));
        System.out.println("file type:"+type);
        String fileName = new Date().getTime()+""+new Random().nextInt(10000)+"cut_"+name.substring(name.lastIndexOf('.'));
        System.out.println("文件路径："+path+":"+fileName); 

        JSONObject joData = (JSONObject) JSONObject.parse(avatar_data);
          // 用户经过剪辑后的图片的大小  
        float x = joData.getFloatValue("x");
        float y = joData.getFloatValue("y");
        float w =  joData.getFloatValue("width");
        float h =  joData.getFloatValue("height");
        
        
        //开始上传
        File targetFile = new File(path, fileName);
        //保存  
        try {  
            if(!targetFile.exists()){  
                targetFile.mkdirs();  
                InputStream is = avatar_file.getInputStream();
                ImageCut.cut(is, targetFile, (int)x,(int)y,(int)w,(int)h);  
                is.close();
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            return  JSON.toJSONString(new Result(null,"上传失败，出现异常："+e.getMessage()));
        }  
        return  JSON.toJSONString(new Result(request.getSession().getServletContext().getContextPath()+dir+fileName,"上传成功!"));
    }*/

/*package com.springjcrop.demo;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/UploadDemo")
public class UploadImgController {
	
	@RequestMapping(value = "/uploadHeadImage",method = RequestMethod.GET)
    public String uploadCropper(){
		return "cropper";
	}
	
	@RequestMapping(value = "/uploadHeadImage",method = RequestMethod.POST)
    public String uploadCropper(
    		@RequestParam(value = "avatar_file",required=false) MultipartFile avatar_file,
			 String avatar_src,String avatar_data, HttpServletRequest request
    ) throws Exception{
        System.out.println("==========Start=============");
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String resourcePath = "resources/uploadImages/";
        if(avatar_file!=null){
            if(FileUploadUtil.allowUpload(avatar_file.getContentType())){
                String fileName = FileUploadUtil.rename(avatar_file.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0,end);
                File dir = new File(realPath + resourcePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir,saveName+"_src.jpg");
                avatar_file.transferTo(file);
                String srcImagePath = realPath + resourcePath + saveName;
                JSONObject jsonObject = JSONObject.fromObject(avatar_data);
                System.out.println(jsonObject);
        		int x = jsonObject.getInt("x");
        		int y = jsonObject.getInt("y");
        		int w = jsonObject.getInt("width");
        		int h = jsonObject.getInt("height");
                //这里开始截取操作
                System.out.println("==========imageCutStart=============");
                ImgCut.imgCut(srcImagePath,x,y,w,h);
                System.out.println("==========imageCutEnd=============");
                //request.getSession().setAttribute("imgSrc",srcImagePath+"_src.jpg");//成功之后显示用
                //request.getSession().setAttribute("imgCut",srcImagePath+"_cut.jpg");//成功之后显示用
            }
        }
        return "success";
    }

}*/
