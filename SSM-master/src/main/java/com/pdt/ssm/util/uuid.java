package com.pdt.ssm.util;

import java.util.UUID;

public class uuid {
    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
