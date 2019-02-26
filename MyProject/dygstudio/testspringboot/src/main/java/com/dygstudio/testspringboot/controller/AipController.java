package com.dygstudio.testspringboot.controller;

import org.beetl.ext.simulate.WebSimulate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/2/26-9:20
 * @Description:
 */
@RestController
@RequestMapping("/api/v1")
public class AipController {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    /**
    @Autowired
    WebSimulate webSimulate;

    @RequestMapping("/**")
    public void simluateJson(HttpServletRequest request, HttpServletResponse response){
        webSimulate.execute(request,response);
    }
    */

}
