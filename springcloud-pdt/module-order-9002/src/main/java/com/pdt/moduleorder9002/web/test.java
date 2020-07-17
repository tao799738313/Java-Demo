package com.pdt.moduleorder9002.web;

import com.pdt.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class test {

    @Autowired
    RedisService redisService;

    @RequestMapping("/")
    public String main(String[] args) {
        return "order-9002";
    }

    @RequestMapping("/redis")
    public String redis(String[] args) {
        redisService.setCacheObject("redis-9002","redis-9002");
        return "order-9002-redis";
    }
}
