package com.dygstudio.testspringboot.entity;

import org.springframework.stereotype.Component;

/**
 * @author: diyaguang
 * @date: 2019/02/18 10:46 AM
 * @description: com.dygstudio.testspringboot.entity
 */
@Component
public class Department {
    public Integer id;
    public String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
