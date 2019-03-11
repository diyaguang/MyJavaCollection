package com.dygstudio.testspringboot2.dao;

import com.dygstudio.testspringboot2.entity.BMyInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BMyInfoDao {
    @Insert("insert into b_myinfo(id,name)values(#{id},#{name})")
    int add(@Param("id") String id,@Param("name") String name);

    @Update("update b_myinfo set name=#{name} where id=#{id}")
    int update(@Param("id") String id,@Param("name") String name);

    @Update("delete from b_myinfo where id=#{id}")
    int delete(@Param("id") String id);

    @Select("select id,name from b_myinfo wehre id=#{id}")
    BMyInfo findMyInfoById(@Param("id") String id);

    @Select("select id,name from b_myinfo")
    List<BMyInfo> findMyInfoList();
}
