package com.dygstudio.testspringboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @author: diyaguang
 * @date: 2019/03/08 3:32 PM
 * @description: com.dygstudio.testspringboot2.controller
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "Greetings from Spring Boot !";
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    CountDownLatch latch;

    @RequestMapping(value = "/redisSendMessage")
    public String testRedisMessageQueueSend(){
        redisTemplate.convertAndSend("chat","Hello from Redis!");
        return "Send OK ! ";
    }
}
