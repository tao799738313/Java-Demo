package com.pdt.ssm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisDao {


    @Autowired
    private RedisTemplate redisTemplate;

    //redisTemplate.opsForList();//操作list
    //redisTemplate.opsForValue();//操作字符串
    //redisTemplate.opsForCluster();//集群时使用
    //redisTemplate.opsForGeo();//地理位置时使用
    //redisTemplate.opsForHash();//操作hash
    //redisTemplate.opsForSet();//操作set
    //redisTemplate.opsForZSet();//操作有序set


    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }
}