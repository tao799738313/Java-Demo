/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-22 23:08:55
 */
package pers.hmm.shop.filter;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import reactor.core.publisher.Mono;
/**
 * 网关限流过滤器（默认基于IP地址）
 * <p>参考地址：https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.0.RC3/single/spring-cloud-gateway.html#_requestratelimiter_gatewayfilter_factory</p>
 * @since jdk 1.8+
 * @author Xd
 *
 */
@Component
public class LimiterFilterFactory extends AbstractGatewayFilterFactory<LimiterFilterFactory.Config>{
	
	private static final Logger logger = LoggerFactory.getLogger(LimiterFilterFactory.class);
	/**
	 * 限流消息内容
	 */
	@Value("${err.msg.limiter}")
	private String errMsg = "（429）Too Many Requests";
	
    private static final Map<String,Bucket> LIMITER_CACHE = new ConcurrentHashMap<String,Bucket>();
	/**
	 * 构造函数
	 */
	public LimiterFilterFactory(){
		super(Config.class);
	}
    /**
     * 获取令牌桶
     * @param config
     * @return
     */
    private Bucket getBucket(Config config) {
    	Refill refill = Refill.greedy(config.getReplenishRate(), Duration.ofSeconds(1));
    	Bandwidth limit = Bandwidth.classic(config.getBurstCapacity(),refill);
    	return Bucket4j.builder().addLimit(limit).build();
    }
    
	public static class Config {
		/*
		 * 允许用户每秒处理多少个请求
		 */
		private int replenishRate = 10;
		/*
		 * 令牌桶的容量，允许在一秒钟内完成的最大请求数
		 */
		private int burstCapacity = 100;
		/*
		 * 限流Key，默认取终端用户的IP地址
		 */
		private String limiterKey = "ip";

		public int getReplenishRate() {
			return replenishRate;
		}

		public void setReplenishRate(int replenishRate) {
			this.replenishRate = replenishRate;
		}

		public int getBurstCapacity() {
			return burstCapacity;
		}

		public void setBurstCapacity(int burstCapacity) {
			this.burstCapacity = burstCapacity;
		}
		public String getLimiterKey() {
			return limiterKey;
		}

		public void setLimiterKey(String limiterKey) {
			this.limiterKey = limiterKey;
		}

		@Override
		public String toString() {
			return new ToStringCreator(this)
					.append("replenishRate", replenishRate)
					.append("burstCapacity", burstCapacity)
					.append("limiterKey", limiterKey)
					.toString();
		}
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		logger.debug("Limiter filter config：{}",config.toString());
		return (exchange, chain) -> {
			String limiterKey = parserLimiterKey(exchange,config.getLimiterKey());
			Bucket bucket = LIMITER_CACHE.computeIfAbsent(limiterKey,k -> getBucket(config));
			logger.debug("IP: {}，TokenBucket Available Tokens: ",limiterKey,bucket.getAvailableTokens());
			if (bucket.tryConsume(1)) {
				return chain.filter(exchange);
			} else {
				//TranResult<Boolean> tranResult = new FailureTranResult<Boolean>(-1, errMsg);
				ServerHttpResponse response = exchange.getResponse();
				HttpHeaders httpHeaders = response.getHeaders();
				httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
				String message = null; //TODO TranUtils.toJson(tranResult);
				DataBuffer data = response.bufferFactory().wrap(message.getBytes());
				return response.writeWith(Mono.just(data));	
			}
		};
	}
	/**
	 * 这里可以定义的类型有很多，后续完善吧
	 * 根据IP限流
	 * 根据URL地址限流
	 * 根据CPU使用率限流，需要开启actuator功能（http://localhost:7001/actuator/metrics/system.cpu.usage）
	 * ...
	 * TODO 后续完善吧
	 * @param exchange
	 * @param limiterKey
	 * @return
	 */
	public String parserLimiterKey(ServerWebExchange exchange,String limiterKey) {
		String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
		if(limiterKey==null || "".equals(limiterKey) || "IP".equals(limiterKey.trim().toUpperCase())){
			return ip;
		} else if("URI".equals(limiterKey.trim().toUpperCase())){
			return exchange.getRequest().getURI().getPath();
		} else if("LIMITER_USER".equals(limiterKey.trim().toUpperCase())){//用户自定义的KEY，需要每次在Header中上送固定值LIMITER_USER
			limiterKey = exchange.getRequest().getHeaders().getFirst("LIMITER_USER");
			if(limiterKey==null || "".equals(limiterKey)){//TODO 此处逻辑貌似不合理，回头在完善吧
				return ip;
			}
			return limiterKey;
		} else {//匹配不到的统一按IP地址限流
			return ip;
		}
	}
}
