package com.dygstudio.testspringboot2.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: diyaguang
 * @date: 2019/03/11 9:58 AM
 * @description: com.dygstudio.testspringboot2.entity
 */
@Configuration
@PropertySource(value="classpath:my.properties")
@ConfigurationProperties(prefix = "com.dygstudio")
public class MyConfigBean {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
