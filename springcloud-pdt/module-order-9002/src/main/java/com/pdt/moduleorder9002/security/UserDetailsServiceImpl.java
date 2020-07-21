package com.pdt.moduleorder9002.security;

import com.pdt.moduleorder9002.model.JwtUserDetails;
import com.pdt.moduleorder9002.model.User;
import com.pdt.moduleorder9002.service.UserService;
import com.pdt.moduleorder9002.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 用户登录认证信息查询
 * @author Louis
 * @date Jun 29, 2019
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("无该用户");
        }
        return new JwtUserDetails(user);
    }
}