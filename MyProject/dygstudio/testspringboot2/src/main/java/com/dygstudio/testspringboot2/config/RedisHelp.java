package com.dygstudio.testspringboot2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author: diyaguang
 * @date: 2019/03/12 10:46 AM
 * @description: com.dygstudio.testspringboot2.config
 */
@Repository
public class RedisHelp {
    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key,String value){
        ValueOperations<String,String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);
    }

    public String getValue(String key){
        ValueOperations<String,String> ops = template.opsForValue();
        return ops.get(key);
    }
}
