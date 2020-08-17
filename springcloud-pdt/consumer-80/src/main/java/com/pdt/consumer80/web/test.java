package com.pdt.consumer80.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@ResponseBody
public class test {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/user")
    public String testUser() {
        // 把服务提供者的spring.application.name填进来 + service名字
        String str = restTemplate.getForObject("http://user/",String.class);
        return str;
    }

    @RequestMapping("/order")
    public String testOrder() {
        // 把服务提供者的spring.application.name填进来 + service名字
        String str = restTemplate.getForObject("http://order/",String.class);
        return str;
    }
}
