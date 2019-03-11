package com.dygstudio.testspringboot2.service;

import com.dygstudio.testspringboot2.dao.BMyInfoDao;
import com.dygstudio.testspringboot2.entity.BMyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/11-16:44
 * @Description:
 */
@Service
public class BMyInfoService {
    @Autowired
    BMyInfoDao myInfoDao;

    public int add(String id,String name){
        return myInfoDao.add(id,name);
    }

    public int update(String id,String name){
        return myInfoDao.update(id,name);
    }

    public int delete(String id){
        return myInfoDao.delete(id);
    }

    public BMyInfo findMyInfoById(String id){
        return myInfoDao.findMyInfoById(id);
    }

    public List<BMyInfo> findMyInfoList(){
        return myInfoDao.findMyInfoList();
    }

}
