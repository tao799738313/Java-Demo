package com.pdt.ssm.util;


import com.pdt.ssm.config;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by taotao on 2019/8/28.
 */
@Controller
public class UpLoadBase64 {

    @RequestMapping(value = "/uploadBase64", method = RequestMethod.POST)
    @ResponseBody
    public Map base64UpLoad(@RequestParam String file,@RequestParam String fileName,HttpServletRequest req, HttpServletResponse res) {
        System.out.println("req"+req);
        Map map = new HashMap();
        try {
            String dataPrix = "";
            String data = "";
//            logger.debug("对数据进行判断");
            if (file == null || "".equals(file)) {
                throw new Exception("上传数据为空");
            } else {
                String[] d = file.split("base64,"); //把base, 切换
                if (d != null && d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    throw new Exception("上传失败，数据不合法");
                }
            }

//            logger.debug("对数据进行解析，获取文件名和流数据");
//            String suffix = "";
//            if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
//                suffix = ".jpg";
//            } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {//data:image/x-icon;base64,base64编码的icon图片数据
//                suffix = ".ico";
//            } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {//data:image/gif;base64,base64编码的gif图片数据
//                suffix = ".gif";
//            } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
//                suffix = ".png";
//            } else {
//                throw new Exception("上传文件格式不合法");
//            }
            String fileId = uuid.getUuid();
            map.put("fileId", fileId);
            map.put("fileName", fileName);

            //动态获取上传文件夹的路径
//                ServletContext context = req.getServletContext();
//                String realPath = context.getRealPath("/upload");//获取本地存储位置的绝对路径
//                File realPathFolder = new File(realPath);

            //这是指定的绝对路径
            String filePath = config.filePath;
            String fileUrl = config.fileUrl;
            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            File realPathFolder = new File(filePath);
            if (!realPathFolder.exists()) {
                realPathFolder.mkdir();
            }
            String newName = fileId + '_' + fileName;//创建一个新的文件名称.后缀名
            FileUtils.writeByteArrayToFile(new File(realPathFolder,newName), bs);
            map.put("newName", newName);
            map.put("fileUrl",fileUrl);
            map.put("url",fileUrl+newName);
            map.put("code", 20000);  //成功的固定字段
            map.put("msg", "上传base64成功");
        } catch (Exception e) {
            map.put("msg", "上传base64失败");
            e.printStackTrace();
        }
        return map;
    }

}
