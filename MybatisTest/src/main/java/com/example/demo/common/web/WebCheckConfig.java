package com.example.demo.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebCheckConfig implements WebMvcConfigurer {
    @Autowired
    private JwtHandler jwtHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> list = Arrays.asList("/data/**", "/login");
        registry.addInterceptor(jwtHandler)
                .addPathPatterns("/api/**")
                .excludePathPatterns(list);
    }
}
