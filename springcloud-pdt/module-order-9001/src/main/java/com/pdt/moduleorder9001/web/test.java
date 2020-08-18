package com.pdt.moduleorder9001.web;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@ResponseBody
public class test {

//    Logger logger = LoggerFactory.getLogger(test.class);

    @RequestMapping("/")
    public String main(String[] args) {

        log.trace("这是trance日志。。。。");
        log.debug("这是debug日志。。。。");
        log.info("这是info日志。。。。");
        log.warn("这是warn日志。。。。");
        log.error("这是error日志。。。。");

        return "order-9001";
    }

    @RequestMapping("/hello")
    public String hello(String[] args) {

        return "order-9001-hello";
    }
}
