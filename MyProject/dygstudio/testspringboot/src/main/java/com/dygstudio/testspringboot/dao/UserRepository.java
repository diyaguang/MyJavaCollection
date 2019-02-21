package com.dygstudio.testspringboot.dao;

import com.dygstudio.testspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/2/21-16:14
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    //
}
