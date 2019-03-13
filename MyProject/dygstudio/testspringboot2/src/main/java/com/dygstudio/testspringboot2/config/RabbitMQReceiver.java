package com.dygstudio.testspringboot2.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/13-10:29
 * @Description:
 */
@Component
public class RabbitMQReceiver {
    private CountDownLatch latch = new CountDownLatch(1);
    public void receiveMessage(String message){
        System.out.println("Received from rabbitMQ <"+message+">");
        latch.countDown();
    }

    public CountDownLatch getLatch(){
        return latch;
    }
}
