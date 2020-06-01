package com.pdt.ssm.aop;

import com.pdt.ssm.config;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//不需要去web.xml里配置filter标签
@WebFilter(filterName = "whiteListFilter",
        urlPatterns = "/*",/*通配符（*）表示对所有的web资源进行拦截*/
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")/*这里可以放一些初始化的参数*/
        })
public class whiteListFilter implements Filter {

    private String filterName;

    public void destroy() {
        /*销毁时调用*/
        System.out.println(filterName + "销毁");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpreq = (HttpServletRequest)req;
        String Origin =  httpreq.getHeader("Origin");
        System.out.println(Origin);
//        String Referer =  httpreq.getHeader("Referer");
        if(StringUtils.isEmpty(Origin)){
            chain.doFilter(req,resp);  //放行
        }else{
            Origin = Origin.split("//")[1];
            System.out.println(Origin);
            if(config.isWhiteList(Origin)){
                chain.doFilter(req,resp);  //放行
            }else{
                System.out.println("不在白名单之内");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        filterName = config.getFilterName();
        System.out.println("过滤器：" + filterName + "启动");
    }


}
