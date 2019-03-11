package com.dygstudio.testspringboot2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/11-15:00
 * @Description:
 */
@Entity(name = "B_Company")
public class BCompany {
    @Id
    private String id;

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

    private String name;
}
