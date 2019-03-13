package com.dygstudio.testspringboot2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * @author: diyaguang
 * @date: 2019/03/12 5:17 PM
 * @description: com.dygstudio.testspringboot2.config
 * CountDownLatch 是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行。利用它可以实现类似计数器的功能。
 * 方法上标注 @Autowired 注解，使用这个注解之后Spring容器创建当前对象的时候就会调用这个方法，完成赋值。
 * 在构造函数中使用 @Autowired。一个构造函数 @Autowired 说明当创建 bean 时，即使在 XML 文件中没有使用 元素配置 bean ，构造函数也会被自动连接。
 * 在属性中使用 @Autowired 注解来除去 setter 方法。
 */
@Configuration
public class RedisMessageQueueReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageQueueReceiver.class);
    private CountDownLatch latch;

    @Autowired
    public RedisMessageQueueReceiver(CountDownLatch latch){
        this.latch = latch;
    }

    public void receiveMessage(String message){
        LOGGER.info("Received <"+ message +">");
        latch.countDown();
    }

    @Bean
    RedisMessageQueueReceiver receiver(CountDownLatch latch){
        return new RedisMessageQueueReceiver(latch);
    }
    @Bean
    CountDownLatch latch(){
        return new CountDownLatch(1);
    }

}
