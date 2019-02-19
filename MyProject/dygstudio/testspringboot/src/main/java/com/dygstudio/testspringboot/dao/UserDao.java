package com.dygstudio.testspringboot.dao;

import com.dygstudio.testspringboot.entity.User;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<User> {
    public List<User> selectSample(User query);
}
