package com.pdt.moduleorder9002.controller;

import com.pdt.common.redis.service.RedisService;
import com.pdt.moduleorder9002.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@ResponseBody
public class test {

    @Autowired
    RedisService redisService;

    @GetMapping("/")
    public String main(String[] args) {

        log.trace("这是trance日志。。。。");
        log.debug("这是debug日志。。。。");
        log.info("这是info日志。。。。");
        log.warn("这是warn日志。。。。");
        log.error("这是error日志。。。。");

        return "order-9002";
    }


    @GetMapping("/redis")
    public String redis(String[] args) {
        redisService.setCacheObject("redis-9002","redis-9002");
        return "order-9002-redis";
    }

    @PreAuthorize("@ss.hasRole('role')")
    @RequestMapping(value="/role")
    public HttpResult role() {
        return HttpResult.ok("role");
    }

    @PreAuthorize("@ss.hasMenu('menu')")
    @RequestMapping(value="/menu")
    public HttpResult menu() {
        return HttpResult.ok("menu");
    }

    @PreAuthorize("@ss.hasMenu('menu2')")
    @RequestMapping(value="/menu2")
    public HttpResult menu2() {
        return HttpResult.ok("menu2");
    }

}
