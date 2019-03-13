package com.dygstudio.testspringboot2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author: diyaguang
 * @date: 2019/03/12 5:42 PM
 * @description: com.dygstudio.testspringboot2.config
 */
@Configuration
public class RedisMessageQueueListener {
    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageQueueReceiver receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }
}
