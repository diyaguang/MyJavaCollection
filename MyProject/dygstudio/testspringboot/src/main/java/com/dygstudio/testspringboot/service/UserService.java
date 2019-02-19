package com.dygstudio.testspringboot.service;

import com.dygstudio.testspringboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/02/18 11:32 AM
 * @description: com.dygstudio.testspringboot.service
 */
@Service
public interface UserService {
    public List<User> getUserByName(String name);
}
