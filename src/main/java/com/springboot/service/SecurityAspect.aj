package com.springboot.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private Authenticator authenticator;

    @Pointcut("execution(* com.springboot..*(..))")
    public void securityAccess() {}

    @Before("securityAccess()")
    public void secure() {
        System.out.println("checking and authenticating user");
        authenticator.Authenticate();
    }
}
