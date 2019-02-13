package com.dygstudio.testspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/2/13-14:11
 * @Description:
 */
@Controller
public class ErrorController extends AbstractErrorController {

    Log log = LogFactory.getLog(ErrorController.class);

    @Autowired
    ObjectMapper objectMapper;

    public ErrorController(){
        super(new DefaultErrorAttributes());
    }

    @RequestMapping("/error")
    public ModelAndView getErrorPath(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> model = Collections.unmodifiableMap(getErrorAttributes(request,false));
        Throwable cause = getCause(request);
        int status = (Integer)model.get("status");
        String message = (String)model.get("message");
        String errorMessage = getErrorMessage(cause);
        log.info(status+","+message,cause);
        response.setStatus(status);
        if(!isJsonRequest(request)){
            ModelAndView view= new ModelAndView("/error.btl");
            view.addAllObjects(model);
            view.addObject("errorMessage",errorMessage);
            view.addObject("status",status);
            view.addObject("cause",cause);
            return view;
        }else{
            Map error = new HashMap();
            error.put("success",false);
            error.put("errorMessage",errorMessage);
            error.put("message",message);
            //writeJson(response,error);
            return null;
        }
    }
    protected Throwable getCause(HttpServletRequest request){
        Throwable error = (Throwable)request.getAttribute("javax.servlet.error.exception");
        if(error!=null){
            //MVC有可能会封装成 ServletException，需要调用 getCause获取真正的异常
            while(error instanceof ServletException && error.getCause()!=null){
                error = ((ServletException)error).getCause();
            }
        }
        return error;
    }
    protected String getErrorMessage(Throwable ex){
        return "服务器错误，请联系管理员";
    }
    protected boolean isJsonRequest(HttpServletRequest request){
        String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");

        if(requestUri!=null && requestUri.endsWith(".json")){
            //也可以根据获取 HTTP头，根据 Accept字段是否包含JSON来进一步判断
            //request.getHeader("Accept").contains("application/json")
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
