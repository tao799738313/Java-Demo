package com.pdt.ssm.util;

import com.pdt.ssm.config;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by taotao on 2019/8/27.
 */
@Controller
public class DownLoad {
    /*
     * 下载方式一：
     * ①获取前台要下载的文件名称
     * ②设置响应类型
     * ③设置下载页显示的文件名
     * ④获取下载文件夹的绝对路径和文件名合并为File类型
     * ⑤将文件复制到浏览器
     */
    @RequestMapping("/download")
    @ResponseBody
    public void download(HttpServletRequest req, HttpServletResponse resp, String fileName) throws Exception {
        String filePath = config.filePath;
        //String realPath = req.getServletContext().getRealPath("/download");//获取下载文件的路径
        File file = new File(filePath,fileName);//把下载文件构成一个文件处理   filename:前台传过来的文件名称

        //设置响应类型  ==》 告诉浏览器当前是下载操作，我要下载东西
        resp.setContentType("application/x-msdownload");
        //设置下载时文件的显示类型(即文件名称-后缀)   ex：txt为文本类型
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        //下载文件：将一个路径下的文件数据转到一个输出流中，也就是把服务器文件通过流写(复制)到浏览器端
        Files.copy(file.toPath(), resp.getOutputStream());//Files.copy(要下载的文件的路径,响应的输出流)
    }

    /*
     * 下载方式二：Spring框架技术
     */
    @RequestMapping(value = "/download2", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletRequest request, String fileName) throws IOException {
        String filePath = config.filePath;
//        String realPath = request.getServletContext().getRealPath("/download");//获取下载文件的路径

        File file = new File(filePath, fileName);//把下载文件构成一个文件处理   filename:前台传过来的文件名称

        HttpHeaders headers = new HttpHeaders();//设置头信息
        String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");//设置响应的文件名

        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // MediaType:互联网媒介类型 contentType：具体请求中的媒体类型信息
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}

