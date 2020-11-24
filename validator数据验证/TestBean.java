package com.louis.springboot.demo.validator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TestBean {
    @NotBlank( message = "userId不能为空")
    private String userId;

    @NotBlank( message = "userName不能为空")
    private String userName;

    // 嵌套验证
    @Valid
    @NotNull( message = "ItemBean不能为空")
    private List<ItemBean> itemBean;



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

    public List<ItemBean> getItemBean() {
        return itemBean;
    }

    public void setItemBean(List<ItemBean> itemBean) {
        this.itemBean = itemBean;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", itemBean=" + itemBean +
                '}';
    }
}
