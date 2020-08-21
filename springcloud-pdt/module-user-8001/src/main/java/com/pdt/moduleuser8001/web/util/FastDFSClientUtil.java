package com.pdt.moduleuser8001.web.util;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;

@Component
public class FastDFSClientUtil {

    @Value("${fdfs.reqHost}")
    private String reqHost;

    @Value("${fdfs.reqPort}")
    private String reqPort;

    @Value("${fdfs.thumbImage.width}")
    private String width;

    @Value("${fdfs.thumbImage.height}")
    private String height;

    @Autowired
    private FastFileStorageClient storageClient;



    public StorePath uploadFile(MultipartFile file) throws IOException{
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return storePath;
    }


    /**
     * 传图片并同时生成一个缩略图
     * "JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public StorePath uploadImageAndCrtThumbImage(MultipartFile file) throws IOException{
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(),file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return storePath;
    }


    //base64字符串转化成图片
    public StorePath uploadBase64(String content, String fileExtension) throws IOException {
        String[] d = content.split("base64,"); //把base, 切换
        ByteArrayInputStream stream = null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(d[1]);
        stream = new ByteArrayInputStream(bytes);
        return storageClient.uploadFile(stream,bytes.length,fileExtension,null);
    }

    public StorePath uploadBase64AndCrtThumbImage(String content, String fileExtension) throws IOException {
        String[] d = content.split("base64,"); //把base, 切换
        ByteArrayInputStream stream = null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(d[1]);
        stream = new ByteArrayInputStream(bytes);
        return storageClient.uploadImageAndCrtThumbImage(stream,bytes.length,fileExtension,null);
    }

    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     * @throws IOException
     */
    public void deleteFile(String storePath){
        if (!StringUtils.hasText(storePath)) {
            throw new NullPointerException();
        }
        storageClient.deleteFile(storePath);
    }



    public InputStream download(String groupName, String path ) {
        System.out.println(path);
        InputStream ins =  storageClient.downloadFile(groupName, path,new DownloadCallback<InputStream>(){
            @Override
            public InputStream recv(InputStream ins) throws IOException {
                // 将此ins返回给上面的ins
                return ins;
            }}) ;
        return ins ;
    }

    /**
     * 封装文件完整URL地址
     * @param storePath
     * @return
     */
    public String getResAccessUrl(StorePath storePath) {
        String fileUrl = "http://" + reqHost + ":" + reqPort + "/" + storePath.getFullPath();
        return fileUrl;
    }

    public String getThumbImageResAccessUrl(StorePath storePath) {
        String fullPath = storePath.getFullPath();
        String[] paths = fullPath.split("\\.");
        String name = paths[0];
        String extName = paths[1];
        String fileUrl = "http://" + reqHost + ":" + reqPort + "/" + name + "_" + width + "x" + height + "." + extName;
        return fileUrl;
    }
}