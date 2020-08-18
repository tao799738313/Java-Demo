package com.pdt.moduleorder9002.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheTransfer {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplateUtil(){
        RedisCache.setRedisTemplate(redisTemplate);
    }
}