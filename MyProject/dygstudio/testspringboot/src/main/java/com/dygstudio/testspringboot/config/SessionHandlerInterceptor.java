package com.dygstudio.testspringboot.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: diyaguang
 * @date: 2019/02/12 2:35 PM
 * @description: com.dygstudio.testspringboot.config
 */
public class SessionHandlerInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        response.sendRedirect("/login.html");
        return false;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Exception{
        //Controller 处理完毕后调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Object handler,Exception ex) throws Exception{
        //页面渲染完毕后，通常用此方法来清除某些资源
    }
}
