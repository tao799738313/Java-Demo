package com.pdt.moduleorder9002.security;


import org.springframework.stereotype.Service;

@Service("ss")
public class PermissionService
{
    public boolean hasPermi(String rolePermission)
    {
        if (rolePermission.equals("pdt"))
        {
            return true;
        }else{
            return false;
        }
    }
}
