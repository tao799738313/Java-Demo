/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-21 14:32:44
 */
package pers.hmm.shop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import pers.hmm.shop.common.utils.GateWayUtils;
import reactor.core.publisher.Mono;
/**
 * 需要验证Token请求的过滤器
 * @see application.yml
 * @since jdk 1.8
 * @author Xd
 *
 */
@Component
public class TokenFilterFactory extends AbstractGatewayFilterFactory<TokenFilterFactory.Config>{
	
	private static final Logger logger = LoggerFactory.getLogger(TokenFilterFactory.class);
	/**
	 * @see application.yml
	 */
	@Value("${err.msg.token}")
	private String errMsg = "Token check failed.";
	
	public TokenFilterFactory(){
		super(Config.class);
	}
	
	public static class Config {
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String jwtToken = exchange.getRequest().getHeaders().getFirst("token");
			logger.info("用户上送Token值：{}",jwtToken);
			if(jwtToken!=null && !"".equals(jwtToken)){
				boolean r = false; //TODO JWTService.verify(jwtToken);
				if(r){//合法
					return chain.filter(exchange);
				} else {
					logger.warn("Token合法性校验不通过.");
				}
			}
			ServerHttpResponse response = exchange.getResponse();
			DataBuffer buffer = GateWayUtils.getFailureDataBuffer(exchange, errMsg);
			return response.writeWith(Mono.just(buffer));
		};
	}
}