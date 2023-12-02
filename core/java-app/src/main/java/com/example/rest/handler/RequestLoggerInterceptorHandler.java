package com.example.rest.handler;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggerInterceptorHandler implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggerInterceptorHandler.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            logger.debug(header + ": " + request.getHeader(header));
        }

        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            logger.debug(param + ": " + request.getParameter(param));
        }

        return true;
    }
}
