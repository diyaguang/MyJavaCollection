package com.dygstudio.testspringboot2.controller;

import com.dygstudio.testspringboot2.entity.ConfigBean;
import com.dygstudio.testspringboot2.entity.MyConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: diyaguang
 * @date: 2019/03/08 5:42 PM
 * @description: com.dygstudio.testspringboot2.controller
 */
@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class LucyController {
    @Autowired
    ConfigBean configBean;

    @Autowired
    MyConfigBean myConfigBean;

    @RequestMapping(value = "/lucy")
    public String miya(){
        return configBean.getGreeting()+">>>>"+configBean.getName()+">>>>"+configBean.getUuid()+">>>>"+configBean.getMax();
    }

    @RequestMapping(value = "/myConfig")
    public String testMyConfig(){
        return myConfigBean.getName() +">>>>>"+myConfigBean.getAge();
    }
}
