package com.dubbo.provider.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Service  //将服务发布出去,注意导dubbo的包
@Component //放在容器中
public class TicketServiceImpl implements TicketService{
    @Value("${server.port}")
    private String port;
    @Value("${dubbo.application.name}")
    private String name;

    @Override
    public String getTicket() {
        String ticket = "卖票员：" + name + "，售票窗口号：" + port;
        System.out.println(ticket);
        return ticket;
    }
}
