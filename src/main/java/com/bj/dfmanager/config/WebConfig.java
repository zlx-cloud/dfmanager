package com.bj.dfmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/model/queryById")
                .excludePathPatterns("/serviceInfo/queryById")
                .excludePathPatterns("/target/targetList")
                .excludePathPatterns("/monitorShow/updateDiskMonitor");
    }

}
