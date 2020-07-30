package com.pdt.moduleorder9002.security;


import org.springframework.stereotype.Service;

@Service("ss")
public class PermissionService {

    public boolean hasRole(String rolePermission) {
        // 查数据库和缓存搜索用户的权限
        if(rolePermission.equals("role")){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasMenu(String menuPermission) {
        // 查数据库和缓存搜索用户的权限
        if(menuPermission.equals("menu")){
            return true;
        }else{
            return false;
        }
    }

}
