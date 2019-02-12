package com.dygstudio.testspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: diyaguang
 * @date: 2019/02/12 4:31 PM
 * @description: com.dygstudio.testspringboot.controller
 */
@Controller
@RequestMapping("/beetl")
public class BeetlController {

    @GetMapping("/showuser.html")
    public ModelAndView showUserInfo(Long id){
        ModelAndView view = new ModelAndView();
        view.addObject("user","user");
        view.setViewName("/userInfo.btl");
        return view;
    }

    @GetMapping("/now.json")
    public @ResponseBody Map now(){
        Map map = new HashMap();
        map.put("time",new Date());
        return map;
    }
}
