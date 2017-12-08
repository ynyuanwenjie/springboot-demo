package com.springboot.service;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Audience {

    @Pointcut("execution(* com.springboot.service.MessageCommunicator.deliver(..))")
    public void performance(){};

    @Before("performance()")
    public void takeSeats() {
        System.out.println("The audience is taking their seats.");
    }

    @Before("performance()")
    public void turnoffCellphones() {
        System.out.println("The audience is turning off their cellphones.");
    }
    @AfterReturning("performance()")
    public void applaud() {
        System.out.println("CLAP CLAP CLAP CLAP CLAP CLAP.");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Boo! we want our money back!");
    }
}
