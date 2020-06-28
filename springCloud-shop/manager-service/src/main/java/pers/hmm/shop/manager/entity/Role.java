package pers.hmm.shop.manager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {

    private Long roleId;
    private String roleName;
    private String roleDesc;
    private String creator;
    private Date createTime;
}
