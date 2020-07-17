package com.pdt.moduleorder9001.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class test {

    @RequestMapping("/")
    public String main(String[] args) {
        return "order-9001";
    }
}
