/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-22 14:52:28
 */
package pers.hmm.shop.filter;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.hmm.shop.common.utils.JsonUtils;
import reactor.core.publisher.Mono;
/**
 * 对IP黑白名单的控制
 * @author Xd
 *
 */
//@Component
public class IPCheckFilter implements GlobalFilter, Ordered {
	
	private static final Logger logger = LoggerFactory.getLogger(IPCheckFilter.class);
	
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		//String ip = RequestUtils.getIpAddr(exchange);	//正式代码
		String ip = exchange.getRequest().getHeaders().getFirst("ip");//测试代码
		/*
		 * 这里可以从Redis或本地文件或数据库读取定义的黑白名单数据
		 */
		//TODO 待业务场景完善
		String[] array = new String[]{
				"127.0.0.1","10.5.96.26"
		};
		if(ArrayUtils.contains(array, ip)){//测试
			URI uri = exchange.getRequest().getURI();
			logger.warn("[IP Filter]：{} 请求 {} 被拒绝.",ip,uri.toString());
			/*
			 * 组织请求参数数据，交付使用方，比如：MQ、Service等
			 */
			//TranParams<String> tranParams = new TranParams<String>(ip);
			//tranParams.addParam("uri", exchange.getRequest().getURI());
			/*
			 * 组织处理结果，返回请求方
			 * 以下代码不需要修改
			 */
			//TranResult<Boolean> tranResult = new FailureTranResult<Boolean>(-1, "非法请求");//TODO 此处中文后续考虑国际化
			ServerHttpResponse response = exchange.getResponse();
			HttpHeaders httpHeaders = response.getHeaders();
			String message = null; //TODO JsonUtils.serialize(tranResult);
			DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			return response.writeWith(Mono.just(buffer));
		}
		return chain.filter(exchange);
	}
}
