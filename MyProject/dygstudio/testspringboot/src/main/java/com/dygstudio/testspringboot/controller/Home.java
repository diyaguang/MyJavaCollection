package com.dygstudio.testspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: diyaguang
 * @date: 2019/02/12 2:11 PM
 * @description: com.dygstudio.testspringboot.controller
 */
@Controller
public class Home {

    @RequestMapping(value = "/")
    @ResponseBody
    public String home(){
        return "the test page!";
    }
}
