package com.dygstudio.testspringboot.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: diyaguang
 * @date: 2019/02/18 10:43 AM
 * @description: com.dygstudio.testspringboot.entity
 */
@Component
@Entity
public class User implements Serializable {
    @Id
    private String id;

    @Column
    private String name;

    @Column(name="create_time")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name="department_id")
    Department department;

    public User(){
        //
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
