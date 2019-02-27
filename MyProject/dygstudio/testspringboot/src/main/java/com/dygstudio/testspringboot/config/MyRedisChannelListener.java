package com.dygstudio.testspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * @author: diyaguang
 * @date: 2019/02/27 2:35 PM
 * @description: com.dygstudio.testspringboot.tools
 */
@Configuration
public class MyRedisChannelListener implements MessageListener {
    public void onMessage(Message message,byte[] pattern){
        byte[] channal = message.getChannel();
        byte[] bs = message.getBody();
        try{
            String content = new String(bs,"UTF-8");
            String p = new String(channal,"UTF-8");
            System.out.println("get "+content+" from "+p);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}
