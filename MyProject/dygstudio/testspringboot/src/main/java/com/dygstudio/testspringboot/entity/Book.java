package com.dygstudio.testspringboot.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: diyaguang
 * @date: 2019/02/27 4:39 PM
 * @description: com.dygstudio.testspringboot.entity
 */
@Component
public class Book {
    String name;
    String message;
    Date postDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
