package com.dygstudio.testspringboot.entity;

import org.springframework.stereotype.Component;

/**
 * @author: diyaguang
 * @date: 2019/02/18 10:43 AM
 * @description: com.dygstudio.testspringboot.entity
 */
@Component
public class User {
    public Integer id;
    public String name;
    public Integer department_id;

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

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }
}
