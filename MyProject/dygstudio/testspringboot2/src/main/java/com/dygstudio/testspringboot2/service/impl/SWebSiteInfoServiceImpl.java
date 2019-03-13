package com.dygstudio.testspringboot2.service.impl;

import com.dygstudio.testspringboot2.dao.SWebSiteInfoDao;
import com.dygstudio.testspringboot2.service.SWebSiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: diyaguang
 * @date: 2019/03/12 9:44 AM
 * @description: com.dygstudio.testspringboot2.service.impl
 */
@Service
public class SWebSiteInfoServiceImpl implements SWebSiteInfoService {
    @Autowired
    SWebSiteInfoDao webSiteInfoDao;

    @Transactional
    public int add(String id,String name){
        return webSiteInfoDao.add(id,name);
    }
}
