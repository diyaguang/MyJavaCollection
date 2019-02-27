package com.dygstudio.testspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * @author: diyaguang
 * @date: 2019/02/27 11:15 AM
 * @description: com.dygstudio.testspringboot.controller
 */
@Controller
@RequestMapping("/redis")
public class RedisStringController {
    @Autowired
    private StringRedisTemplate redisClient;

    @RequestMapping("/test")
    public @ResponseBody String evn(String para) throws  Exception{
        redisClient.opsForValue().set("testenv",para);
        String str = redisClient.opsForValue().get("testenv");
        return str;
    }

    @RequestMapping("/connectionset.html")
    public @ResponseBody String connectionSet(final String key,final String value) throws Exception{
        redisClient.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException{
                try{
                    connection.set(key.getBytes(),value.getBytes());
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
        return "success";
    }
}
