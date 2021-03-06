package com.pdt.ssm.util;

import com.pdt.ssm.config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taotao on 2019/8/27.
 */

/**
 * 上传文件：
 * 使用MultipartFile类型接收前台上传的文件数据
 * 接收数据时要指定接收方式-->RequestMethod.POST
 *
 * @author 郑清
 */
@Controller
public class Ckeditor5 {

    @RequestMapping("/ckeditor5")
    @ResponseBody //不写会默认返回当前路径！！
    public Map upload(@RequestParam("upload") MultipartFile upload, HttpServletRequest req) throws Exception, IOException {
        Map map = saveFile(upload, req);
        return map;
    }

    private Map saveFile(MultipartFile file, HttpServletRequest req) {
        Map map = new HashMap();
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String fileId = uuid.getUuid();
                String fileName = file.getOriginalFilename();//1.jpg 获取上传文件的全名加后缀
                map.put("fileId",fileId);
                map.put("fileName",fileName);

                //动态获取上传文件夹的路径,这是相对路径
                //ServletContext context = req.getServletContext();
                //获取本地存储位置的绝对路径,也就是打包文件的真实路径
                //String realPath = context.getRealPath("/upload");

                //这是指定的绝对路径
                String filePath = config.filePath;
                String fileUrl = config.fileUrl;
                File realPathFolder = new File(filePath);

                if (!realPathFolder.exists()) {
                    realPathFolder.mkdir();
                }
                String newName = fileId + "_" + fileName;//创建一个新的文件名称    FilenameUtils.getExtension(name):获取文件后缀名
                File f = new File(realPathFolder,newName);

                map.put("newName", newName);
                map.put("fileUrl",fileUrl);
                map.put("url",fileUrl+newName);  //必要字段
                map.put("uploaded", 1);  //必要字段
                map.put("code", 2000);  //成功的固定字段
                map.put("msg", "上传ckeditor5成功");
                file.transferTo(f);//将上传的文件存储到指定位置
            } catch (Exception e) {
                map.put("success", 0); //必要字段
                map.put("msg", "上传ckeditor5失败");
                e.printStackTrace();
            }
        } else {
            map.put("success", 0); //必要字段
            map.put("msg", "上传ckeditor5失败");
        }
        return map;
    }

}

