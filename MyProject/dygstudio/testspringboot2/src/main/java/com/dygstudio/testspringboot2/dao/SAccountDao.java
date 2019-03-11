package com.dygstudio.testspringboot2.dao;

import com.dygstudio.testspringboot2.entity.SAccount;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/03/11 11:24 AM
 * @description: com.dygstudio.testspringboot2.dao
 */
public interface SAccountDao {
    int add(SAccount account);
    int update(SAccount account);
    int delete(String id);
    SAccount findAccountById(String id);
    List<SAccount> findAccountList();
}
