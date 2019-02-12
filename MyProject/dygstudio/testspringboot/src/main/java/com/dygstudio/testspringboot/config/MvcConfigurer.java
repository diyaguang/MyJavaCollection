package com.dygstudio.testspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: diyaguang
 * @date: 2019/02/12 2:28 PM
 * @description: com.dygstudio.testspringboot.config
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/admin/**");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**");
        registry.addMapping("/api/**").allowedOrigins("http://domain2.com").allowedMethods("POST","GET");
    }
    public void addFormatters(FormatterRegistry registry){
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }
    public void addViewControllers(ViewControllerRegistry registry){
        //index.html 的请求，设置返回的视图为 index.btl
        registry.addViewController("/index.html").setViewName("/index.btl");
        //对所有以 .do结尾的请求重定向到 /index.html 请求
        registry.addRedirectViewController("/**/*.do","/index.html");

    }
}
