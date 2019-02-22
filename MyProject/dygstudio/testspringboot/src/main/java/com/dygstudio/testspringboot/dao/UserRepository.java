package com.dygstudio.testspringboot.dao;

import com.dygstudio.testspringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/2/21-16:14
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    //
    @Query("select u from User u where u.name=?1 and u.department.id=?2")
    public User findUser(String name,String departmentId);

    @Query(value = "select * from user where name=?1 and department_id=?2",nativeQuery = true)
    public User nativeQuery(String name,String departmentId);

    @Query(value = "select department_id,count(*) from user group by depatment_id",nativeQuery = true)
    public List<Object[]> queryUserCount();

    @Query(value = "select u from User u where u.department.id=?1")
    public Page<User> queryUsers(String departmentId, Pageable page);

    @Modifying
    @Query("update User u set u.name=?1  where u.id=?2")
    public int updateName(String name,String id);

}
