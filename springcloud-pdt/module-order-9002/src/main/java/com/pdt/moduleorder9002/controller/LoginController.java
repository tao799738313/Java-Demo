package com.pdt.moduleorder9002.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.pdt.moduleorder9002.model.User;
import com.pdt.moduleorder9002.security.JwtAuthenticatioToken;
import com.pdt.moduleorder9002.utils.SecurityUtils;
import com.pdt.moduleorder9002.vo.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 * @author Louis
 * @date Jun 29, 2019
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody User user, HttpServletRequest request) throws IOException {

        String username = user.getUsername();
        String password = user.getPassword();

        // 系统登录认证
        JwtAuthenticatioToken jwtAuthenticatioToken = SecurityUtils.login(request, username, password, authenticationManager);

        // 把 token 存进 redis 里
        String token = jwtAuthenticatioToken.getToken();


        return HttpResult.ok(token);
    }

      // 这个接口不用写
//    @PostMapping(value = "/logout")
//    public void logout() throws IOException {
//
//    }


}