package com.dygstudio.testspringboot2.service.impl;

import com.dygstudio.testspringboot2.dao.SAccountDao;
import com.dygstudio.testspringboot2.entity.SAccount;
import com.dygstudio.testspringboot2.service.SAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2019/03/11 11:54 AM
 * @description: com.dygstudio.testspringboot2.service.impl
 */
@Service
public class SAccountServiceImpl implements SAccountService {

    @Autowired
    SAccountDao accountDao;

    @Override
    public int add(SAccount account){
        return accountDao.add(account);
    }
    @Override
    public int update(SAccount account){
        return accountDao.update(account);
    }
    @Override
    public int delete(String id){
        return accountDao.delete(id);
    }
    @Override
    public SAccount findAccountById(String id){
        return accountDao.findAccountById(id);
    }
    @Override
    public List<SAccount> findAccountList(){
        return accountDao.findAccountList();
    }
}
