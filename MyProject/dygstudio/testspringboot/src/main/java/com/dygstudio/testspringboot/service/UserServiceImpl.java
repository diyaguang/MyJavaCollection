package com.dygstudio.testspringboot.service;

import com.dygstudio.testspringboot.dao.UserDao;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: diyaguang
 * @date: 2019/02/18 11:32 AM
 * @description: com.dygstudio.testspringboot.service
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    SQLManager sqlManager;
}
