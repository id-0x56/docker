package com.example.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.rest.handler.RequestLoggerInterceptorHandler;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLoggerInterceptorHandler requestLoggerInterceptorHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.requestLoggerInterceptorHandler);
    }
}
