package com.dubbo.consumer.controller;

import com.dubbo.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/buyTicket", method = RequestMethod.GET)
    public String buyTicket(){
        String ticket = userService.buyTicket();
        return ticket;
    }
}
