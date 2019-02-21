package com.dygstudio.testspringboot.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: diyaguang
 * @date: 2019/02/18 10:46 AM
 * @description: com.dygstudio.testspringboot.entity
 */
@Component
@Entity
public class Department {

    @Id
    public Integer id;

    @Column
    public String name;

    @OneToMany(mappedBy = "department")
    private Set<User> users = new HashSet<User>();


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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
