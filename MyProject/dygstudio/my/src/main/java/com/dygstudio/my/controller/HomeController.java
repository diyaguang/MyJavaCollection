package com.dygstudio.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/20-15:37
 * @Description:
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView Index(){
        ModelAndView view = new ModelAndView();
        view.addObject("user","user");
        view.setViewName("/index.btl");
        return view;
    }
}
