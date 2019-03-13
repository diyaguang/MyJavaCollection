package com.dygstudio.testspringboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: diyaguang
 * @date: 2019/03/12 9:28 AM
 * @description: com.dygstudio.testspringboot2.dao
 */
@Mapper
public interface SWebSiteInfoDao {
    int add(@Param("id") String id,@Param("name") String name);
}

