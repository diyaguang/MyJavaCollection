package com.dygstudio.testspringboot.service;

import com.dygstudio.testspringboot.dao.UserDao;
import com.dygstudio.testspringboot.dao.UserDaoImpl;
import com.dygstudio.testspringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/02/18 11:32 AM
 * @description: com.dygstudio.testspringboot.service
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDaoImpl userDaoImpl;

    @Autowired
    UserDao userDao;

    public List<User> getUserByName(String name){
        User query = new User();
        query.setName(name);
        List<User> list = userDao.selectSample(query);
        return list;
    }
}
