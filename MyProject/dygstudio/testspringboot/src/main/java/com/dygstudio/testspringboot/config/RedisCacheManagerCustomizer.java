package com.dygstudio.testspringboot.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/4-16:37
 * @Description:这个例子实验的还是有问题，注释的代码没有定义，不能使用。参考代码中，和现在实现的方式不同，需要再理解。
 */
@Configuration
public class RedisCacheManagerCustomizer {
    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer(){
        return new CacheManagerCustomizer<RedisCacheManager>() {
            @Override
            public void customize(RedisCacheManager cacheManager) {
                Map<String,Long> expires = new HashMap<String,Long>();
                expires.put("menu",60l);
                //cacheManager.setExpires(expires);
            }
        };
    }
}
