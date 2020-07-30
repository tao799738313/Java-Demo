package com.pdt.moduleuser8001.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class test {

    @RequestMapping("/")
    public String main(String[] args) {
        return "user-8001";
    }

}