package com.dygstudio.testspringboot.service;

import com.dygstudio.testspringboot.dao.UserDao;
import com.dygstudio.testspringboot.dao.UserDaoImpl;
import com.dygstudio.testspringboot.dao.UserRepository;
import com.dygstudio.testspringboot.entity.Department;
import com.dygstudio.testspringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    EntityManager em;

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

    public List<User> getByExample(String name){
        User user = new User();
        Department dept = new Department();
        user.setName(name);
        dept.setId(1);
        user.setDepartment(dept);
        Example<User> example = Example.of(user);
        List<User> list = userRepository.findAll(example);
        return list;
    }

    public Page<User> queryUser2(String departmentId, Pageable page){
        StringBuilder baseJpql = new StringBuilder("from User u where 1=1");
        Map<String,Object> paras = new HashMap<String,Object>();
        if(departmentId!=null){
            baseJpql.append("and u.department.id=:deptId");
            paras.put("deptId",departmentId);
        }
        long count = getQueryCount(baseJpql,paras);
        if(count == 0){
            return new PageImpl<>(Collections.emptyList(),page,0);
        }
        List list = getQueryResult(baseJpql,paras,page);
        Page ret = new PageImpl(list,page,count);
        return ret;
    }

    private Long getQueryCount(StringBuilder baseJpql,Map<String,Object> paras){
        Query query = em.createQuery("select count(1)"+baseJpql.toString());
        setQueryParameter(query,paras);
        Number number = (Number)query.getSingleResult();
        return number.longValue();
    }

    private void setQueryParameter(Query query,Map<String,Object> paras){
        for(Map.Entry<String,Object> entry:paras.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
    }

    private List getQueryResult(StringBuilder baseJpql,Map<String,Object> paras,Pageable page){
        Query query = em.createQuery("select u "+baseJpql.toString());
        setQueryParameter(query,paras);
        query.setFirstResult((page.getPageNumber()-1)*page.getPageSize());
        query.setMaxResults(page.getPageNumber());
        List list = query.getResultList();
        return list;
    }
}
