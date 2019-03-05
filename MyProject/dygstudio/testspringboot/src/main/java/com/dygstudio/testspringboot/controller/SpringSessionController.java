package com.dygstudio.testspringboot.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/5-15:29
 * @Description:
 */
@Controller
@RequestMapping("/session")
public class SpringSessionController {
    Log log = LogFactory.getLog(SpringSessionController.class);

    @RequestMapping("/put.html")
    public @ResponseBody String putSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info(session.getClass());
        log.info(session.getId());
        String name = "diyaguang";
        session.setAttribute("user",name);
        return "hay,"+name;
    }
}
