package com.pdt.moduleuser8001.web.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.pdt.moduleuser8001.web.util.FastDFSClientUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class UploadController {

    @Autowired
    private FastDFSClientUtil dfsClient;

    @Value("${fdfs.reqHost}")
    private String reqHost;

    @Value("${fdfs.reqPort}")
    private String reqPort;

    @PostMapping("/upload")
    @ResponseBody
    public Map upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        Map res =  new HashMap<>();
        StorePath storePath =  dfsClient.uploadFile(file);
        res.put("success",true);
        res.put("path",dfsClient.getResAccessUrl(storePath));
        return res;
    }

    @PostMapping("/uploadImageAndCrtThumbImage")
    @ResponseBody
    public Map uploadImageAndCrtThumbImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        Map res =  new HashMap<>();
        StorePath storePath =  dfsClient.uploadImageAndCrtThumbImage(file);
        res.put("success",true);
        res.put("path",dfsClient.getResAccessUrl(storePath));
        res.put("thumbPath",dfsClient.getThumbImageResAccessUrl(storePath));
        return res;
    }


    @PostMapping("/uploadbase64")
    @ResponseBody
    public Map uploadbase64(String base64, HttpServletRequest request) throws IOException {
        Map res =  new HashMap<>();
        StorePath storePath =  dfsClient.uploadBase64(base64,"jpg");
        res.put("success",true);
        res.put("path",dfsClient.getResAccessUrl(storePath));
        return res;
    }

    @PostMapping("/uploadbase64AndCrtThumbImage")
    @ResponseBody
    public Map uploadbase64AndCrtThumbImage(String base64, HttpServletRequest request) throws IOException {

        Map res =  new HashMap<>();
        StorePath storePath =  dfsClient.uploadBase64AndCrtThumbImage(base64,"jpg");
        res.put("success",true);
        res.put("path",dfsClient.getResAccessUrl(storePath));
        res.put("thumbPath",dfsClient.getThumbImageResAccessUrl(storePath));
        return res;
    }

    /*
     * http://localhost/download?filePath=group1/M00/00/00/wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
     */
    @RequestMapping("/download")
    @ResponseBody
    public void download(String filePath , HttpServletRequest request , HttpServletResponse response) throws IOException {

        String[] paths = filePath.split("/");

// 方式一 从 dfsClient 下，集群时不能用
        String groupName = null ;
        for (String item : paths) {
            if (item.indexOf("group") != -1) {
                groupName = item;
                break;
            }
        }
        String path = filePath.substring(filePath.indexOf(groupName) + groupName.length() + 1, filePath.length());
        InputStream input = dfsClient.download(groupName, path);


// 方式二 请求进行转发
//        URL url = new URL("http://" + reqHost + ":" + reqPort + "/" + filePath);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        System.out.println("发出请求");
//        conn.setDoOutput(true);//需要用到输出流
//        conn.setDoInput(true);//需要用到输入流
//        conn.setRequestMethod("GET");
//        conn.connect();
//        // 读取服务器端返回的内容
//        InputStream input = conn.getInputStream();

        //根据文件名获取 MIME 类型
        String fileName = paths[paths.length-1] ;
        System.out.println("fileName :" + fileName); // wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
        String contentType = request.getServletContext().getMimeType(fileName);
        String contentDisposition = "attachment;filename=" + fileName;

        // 设置头
        response.setHeader("Content-Type",contentType);
        response.setHeader("Content-Disposition",contentDisposition);

        // 获取绑定了客户端的流
        ServletOutputStream output = response.getOutputStream();

        // 把输入流中的数据写入到输出流中
        IOUtils.copy(input,output);
        input.close();

    }

    /**
     * http://localhost/deleteFile?filePath=group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     * @param fileName  group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteFile")
    @ResponseBody
    public String delFile(String filePath , HttpServletRequest request ,HttpServletResponse response)  {

        dfsClient.deleteFile(filePath);

        return "删除成功";
    }


}