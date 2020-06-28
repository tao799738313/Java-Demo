/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-22 15:20:56
 */
package pers.hmm.shop.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

/**
 * Request 请求的一些工具类
 * 
 * @author Xd
 *
 */
public class GateWayUtils {
	static Logger logger = LoggerFactory.getLogger(GateWayUtils.class);
	
	public static DataBuffer getFailureDataBuffer(ServerWebExchange exchange,String errMsg) {
		//TranResult<Boolean> tranResult = new FailureTranResult<Boolean>(-1, errMsg);
		ServerHttpResponse response = exchange.getResponse();
		HttpHeaders httpHeaders = response.getHeaders();
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		String message = null;//TODO TranUtils.toJson(tranResult);
		DataBuffer data = response.bufferFactory().wrap(message.getBytes());
		return data;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(ServerWebExchange exchange) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		String ip = headers.getFirst("x-forwarded-for");
		logger.debug("x-forwarded-for ip {}", ip);
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("Proxy-Client-IP");
			logger.debug("Proxy-Client-IP ip {}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("WL-Proxy-Client-IP");
			logger.debug("WL-Proxy-Client-IP ip {}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("HTTP_CLIENT_IP");
			logger.debug("HTTP_CLIENT_IP ip {}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
			logger.debug("HTTP_X_FORWARDED_FOR ip {}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("X-Real-IP");
			logger.debug("X-Real-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = exchange.getRequest().getRemoteAddress().getHostString();
			logger.debug("getRemoteAddr ip {}", ip);
		}
//		if("0:0:0:0:0:0:0:1".equals(ip)){
//			ip = "127.0.0.1";
//		}
		logger.debug("客户端IP {}", ip);
		return ip;
	}
}
