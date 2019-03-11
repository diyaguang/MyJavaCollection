package com.dygstudio.testspringboot2.service;

import com.dygstudio.testspringboot2.entity.SAccount;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/03/11 11:53 AM
 * @description: com.dygstudio.testspringboot2.service
 */
public interface SAccountService {
    int add(SAccount account);
    int update(SAccount account);
    int delete(String id);
    SAccount findAccountById(String id);
    List<SAccount> findAccountList();
}
