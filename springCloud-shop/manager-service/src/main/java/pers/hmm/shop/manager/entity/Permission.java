package pers.hmm.shop.manager.entity;

import lombok.Data;

import java.util.List;

@Data
public class Permission {

    private Long permissionId;
    private String permissionName;
    private String permissionDesc;
    private String url;
    private List<Role> roles;
}
