package org.example.web_userlogintest.config;

import org.example.web_userlogintest.interceptor.LoginCheckinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckinterceptor LoginCheckInterceptor;
    @Override
    //拦截器
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(LoginCheckInterceptor).addPathPatterns("/**");
    }


//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //扫描static下的所有html页面
//        registry.addResourceHandler("classpath:/static/*.html");
//        //扫描static下的所有资源
//        registry.addResourceHandler("/static/**");
//    }
}

