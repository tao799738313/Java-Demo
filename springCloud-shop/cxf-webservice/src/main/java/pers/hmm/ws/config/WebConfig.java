package pers.hmm.ws.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.hmm.ws.service.UserService;

import javax.xml.ws.Endpoint;

/**
 * @Author: hmm
 * @Created: 2019/10/15
 * @Description:
 * @Modified by:
 */
@Configuration
public class WebConfig {
    /**这里需要注意  由于springmvc 的核心类 为DispatcherServlet
    *此处若不重命名此bean的话 原本的mvc就被覆盖了。可查看配置类：DispatcherServletAutoConfiguration
    *一种方法是修改方法名称 或者指定bean名称
    *这里需要注意 若beanName命名不是 cxfServletRegistration 时，会创建两个CXFServlet的。
    *具体可查看下自动配置类：Declaration org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration
    *也可以不设置此bean 直接通过配置项 cxf.path 来修改访问路径的
    */
    @Bean("cxfServletRegistration")
    public ServletRegistrationBean dispatcherServlet() {
        //注册servlet 拦截/ws 开头的请求 不设置 默认为：/services/*
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private Bus bus;

    /*
     * 发布endpoint 一个服务发布一个
     */
    @Bean
    public Endpoint UserEndpoint(UserService userService) {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/user");
        return endpoint;
    }

    /**
     * 这里替换service就行
     * @param userService
     * @return
     */
    @Bean
    public Endpoint OtherEndpoint(UserService userService) {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/user");
        return endpoint;
    }
}
