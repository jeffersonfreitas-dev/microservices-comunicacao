package com.microservice.productapi.config.interceptor;

import com.microservice.productapi.config.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(isOptional(request)) return true;

        var authorization = request.getHeader(AUTHORIZATION);
        service.validatorAuthorized(authorization);
        return true;
    }

    private boolean isOptional(HttpServletRequest request){
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
}
