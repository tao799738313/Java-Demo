/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-22 13:53:30
 */
package pers.hmm.shop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 * 这是一个全局过滤器的样例代码
 * @since jdk 1.8
 * @author Xd
 *
 */
//@Component
public class SampleGlobalFilter implements GlobalFilter, Ordered {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);
	/**
	 * 数字越小，越优先执行
	 */
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		//PRE
		logger.debug("过滤前执行");
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			//POST
			logger.debug("过滤后执行");
		}));
	}
}