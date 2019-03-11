package com.dygstudio.testspringboot2.entity;

import org.springframework.stereotype.Component;

/**
 * @author: diyaguang
 * @date: 2019/03/11 11:22 AM
 * @description: com.dygstudio.testspringboot2.entity
 */
@Component
public class SAccount {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
