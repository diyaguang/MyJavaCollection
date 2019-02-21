package com.dygstudio.testspringboot.service;

import com.dygstudio.testspringboot.dao.UserDao;
import com.dygstudio.testspringboot.dao.UserDaoImpl;
import com.dygstudio.testspringboot.dao.UserRepository;
import com.dygstudio.testspringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/02/18 11:32 AM
 * @description: com.dygstudio.testspringboot.service
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDaoImpl userDaoImpl;

    @Autowired
    UserDao userDao;

    @Autowired
    UserRepository userRepository;

    public List<User> getUserByName(String name){
        User query = new User();
        query.setName(name);
        List<User> list = userDao.selectSample(query);
        return list;
    }

    public String addUser(User user){
        userRepository.save(user);
        String id = user.getId();
        return id;
    }

    public List<User> getAllUser(int page,int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<User> pageObject = userRepository.findAll(pageRequest);
        int totalPage = pageObject.getTotalPages();
        long count = pageObject.getTotalElements();
        return pageObject.getContent();
    }
}
