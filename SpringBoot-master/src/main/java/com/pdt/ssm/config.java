package com.pdt.ssm;

import java.util.ArrayList;

public class config {
    //public static final String filePath = "E:\\nginx-1.16.1\\html\\uploadFile";//本地
    public static final String filePath = "/usr/local/nginx/html/uploadFile/"; //线上
    //public static final String fileUrl = "http://localhost/uploadFile/";  //本地
    public static final String fileUrl = "http://xx.xx.xx.xx/uploadFile/";  //线上
    public static final ArrayList whiteList = new ArrayList();
    static {
        whiteList.add("127.0.0.1:80");
        whiteList.add("127.0.0.1:3000");
        whiteList.add("127.0.0.1:8080");
        whiteList.add("127.0.0.1:8848");
    }

    public static boolean isWhiteList(String ip) {
       if(whiteList.indexOf(ip)==-1){
           return false;
       }
        return true;
    }

}
