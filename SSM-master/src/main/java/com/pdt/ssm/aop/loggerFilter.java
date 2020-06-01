package com.pdt.ssm.aop;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


//不需要去web.xml里配置filter标签
@WebFilter(filterName = "loggerFilter",
        urlPatterns = "/*",/*通配符（*）表示对所有的web资源进行拦截*/
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")/*这里可以放一些初始化的参数*/
        })
public class loggerFilter implements Filter {

    private String filterName;

    private Logger logger = LogManager.getLogger(loggerFilter.class);

    public void destroy() {
        /*销毁时调用*/
        System.out.println(filterName + "销毁");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpreq = (HttpServletRequest)req;
        System.out.println(new Date().getTime()+"---"+httpreq.getMethod()+"---"+httpreq.getRequestURL());
        /*过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理*/
        chain.doFilter(req,resp);  //放行
    }

    public void init(FilterConfig config) throws ServletException {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        filterName = config.getFilterName();
//        logger.info("过滤器logger");
        System.out.println("过滤器：" + filterName + "启动");

    }

}
