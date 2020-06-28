package com.dubbo.consumer.service;

import com.dubbo.provider.service.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service  //注入到容器中
public class UserServiceImpl implements UserService {
    @Reference  //远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
    TicketService ticketService;

    @Override
    //我们需要去拿去注册中心的服务
    public String buyTicket() {
        String buyTicket = "我是买票的，我在 " + ticketService.getTicket() + "，买到的门票";
        System.out.println(buyTicket);
        return buyTicket;
    }
}
