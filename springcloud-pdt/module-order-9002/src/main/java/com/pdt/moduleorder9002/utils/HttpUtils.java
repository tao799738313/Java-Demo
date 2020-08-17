package com.pdt.moduleorder9002.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pdt.moduleorder9002.vo.HttpResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;


/**
 * HTTP工具类
 * @author Louis
 * @date Jun 29, 2019
 */
public class HttpUtils {

	/**
	 * 获取HttpServletRequest对象
	 * @return
	 */
	public static HttpServletRequest getRequest()
	{
		return getRequestAttributes().getRequest();
	}

	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		return (ServletRequestAttributes) attributes;
	}

	public static HttpServletResponse getResponse()
	{
		return getRequestAttributes().getResponse();
	}
	/**
	 * 输出信息到浏览器
	 * @param response
	 * @param message
	 * @throws IOException
	 */
	public static void write(HttpServletResponse response, Object data) throws IOException {
		response.setContentType("application/json; charset=utf-8");
//        HttpResult result = HttpResult.ok(data);
        String json = JSONObject.toJSONString(data);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
	}

	public static void write(Object data) throws IOException {
		HttpServletResponse response = getResponse();
		response.setContentType("application/json; charset=utf-8");
//        HttpResult result = HttpResult.ok(data);
		String json = JSONObject.toJSONString(data);
		response.getWriter().print(json);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
}
