package com.pdt.ssm.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by taotao on 2019/7/31.
 */
//这个文件夹是放跟数据库字段对应数据的class类的
public class TestBean {
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", list=" + list +
                '}';
    }
}
