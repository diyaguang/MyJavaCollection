package com.dygstudio.testspringboot.config;

import org.beetl.core.GroupTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/2/13-17:19
 * @Description:
 */
@Configuration
public class BeetlExtConfig {
    GroupTemplate groupTemplate;

    @PostConstruct
    public void config(){
        //
    }
}
