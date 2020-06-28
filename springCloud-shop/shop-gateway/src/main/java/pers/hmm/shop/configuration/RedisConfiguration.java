/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-21 14:37:15
 */
package pers.hmm.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.timeout}")
	private int timeout;

	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		//Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		//ObjectMapper om = new ObjectMapper();
		//om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		//om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		//jackson2JsonRedisSerializer.setObjectMapper(om);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		//template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		JdkSerializationRedisSerializer valueSerializer = new JdkSerializationRedisSerializer();
		template.setValueSerializer(valueSerializer);
		// hash的value序列化方式采用jackson
		//template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
}