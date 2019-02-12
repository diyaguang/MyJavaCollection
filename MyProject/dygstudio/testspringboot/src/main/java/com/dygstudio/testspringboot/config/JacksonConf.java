package com.dygstudio.testspringboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author: diyaguang
 * @date: 2019/02/12 4:59 PM
 * @description: com.dygstudio.testspringboot.config
 */
@Configuration
public class JacksonConf {
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }
}
