package com.louis.springboot.demo.validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemBean {
    @NotBlank( message = "itemId不能为空")
    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "itemId='" + itemId + '\'' +
                '}';
    }
}
