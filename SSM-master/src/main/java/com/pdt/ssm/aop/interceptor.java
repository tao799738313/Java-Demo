package com.pdt.ssm.aop;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by taotao on 2019/8/28.
 */
public class interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
                             Object arg2) throws Exception {
//        在这个可以判断访问路径，
//        常用于 判断req是不是有指定的token或者session表示登录状态，
//           也可以用来判断访问路径存不存在，没有返回404页面
//        返回true是放行，
//        如果不放心，先用req.getRequestDispatcher(路径).forword(req,res)，再return false
        return true;
    }
}
