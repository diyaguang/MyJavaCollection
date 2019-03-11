package com.dygstudio.testspringboot2.api;

import com.dygstudio.testspringboot2.entity.BMyInfo;
import com.dygstudio.testspringboot2.service.BMyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/11-16:48
 * @Description:
 */
@RestController
@RequestMapping(value = "/api/myinfo")
public class BMyInfoAPI {
    @Autowired
    BMyInfoService myInfoService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<BMyInfo> getMyInfoList(){
        return myInfoService.findMyInfoList();
    }
}
