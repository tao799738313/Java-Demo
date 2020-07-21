package com.pdt.moduleorder9002.controller;

import com.pdt.common.redis.service.RedisService;
import com.pdt.moduleorder9002.vo.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PreAuthorize("@ss.hasPermi('pdt')")
    @GetMapping(value="/pdt")
    public HttpResult pdt() {
        return HttpResult.ok("pdt");
    }

    @PreAuthorize("@ss.hasPermi('pdt2')")
    @GetMapping(value="/pdt2")
    public HttpResult pdt2() {
        return HttpResult.ok("pdt2");
    }

}
