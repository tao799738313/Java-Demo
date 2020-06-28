/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-22 13:53:30
 */
package pers.hmm.shop.filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import pers.hmm.shop.common.utils.GateWayUtils;
import reactor.core.publisher.Mono;
/**
 * 防SQL注入安全检查（全局过滤器）
 * 如果关闭，请注释掉 @Component 即可
 * @see application.yml
 * @since jdk 1.8
 * @author Xd
 *
 */
@Component
public class SQLFilter implements GlobalFilter, Ordered {
	
	private static final Logger logger = LoggerFactory.getLogger(SQLFilter.class);
	/**
	 * 看配置文件
	 */
	@Value("${sql.injection}")
	private String keywords = "";
	/**
	 * 数字越小，越优先执行
	 */
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		boolean checkResut = false;
		MultiValueMap<String, String> mvp = exchange.getRequest().getQueryParams();//MVP 好熟悉的名字，就叫这个名字吧 哈哈
		Iterator<Map.Entry<String,List<String>>> it = mvp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<String>> entry = it.next();
			String key = entry.getKey();
			List<String> values = entry.getValue();//如果是相同的名字会以List<String>的方式组织
			for(String value:values){
				String[] array = keywords.split("\\|");
				for(String keyword : array){
					if(value!=null && value.toLowerCase().indexOf(keyword)!=-1){
						logger.warn("参数名:{} 值为：{} 含有危险字符 {}.",new Object[]{key,value,keyword});
						checkResut = true;
						break;
					}
				}
			}
		}
		if(checkResut){
			ServerHttpResponse response = exchange.getResponse();
			DataBuffer buffer = GateWayUtils.getFailureDataBuffer(exchange, "参数不合法");
			return response.writeWith(Mono.just(buffer));					
		}
		return chain.filter(exchange);
	}
}