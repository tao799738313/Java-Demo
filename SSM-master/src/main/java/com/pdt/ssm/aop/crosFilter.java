package com.pdt.ssm.aop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//不需要去web.xml里配置filter标签
@WebFilter(filterName = "crosFilter",
        urlPatterns = "/*",/*通配符（*）表示对所有的web资源进行拦截*/
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")/*这里可以放一些初始化的参数*/
        })
public class crosFilter implements Filter {

    private String filterName;

    public void destroy() {
        /*销毁时调用*/
        System.out.println(filterName + "销毁");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println(filterName);
        /*过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理*/
        HttpServletResponse httpResponse = (HttpServletResponse)resp;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpResponse.setHeader("Access-Control-Max-Age", "1800");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With,Content-Type,Accept,accept,Access-Control-Request-Method,Access-Control-Request-Headers,token,x-token");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
          chain.doFilter(req,resp);  //放行
    }

    public void init(FilterConfig config) throws ServletException {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        filterName = config.getFilterName();
        System.out.println("过滤器：" + filterName + "启动");
    }

}

