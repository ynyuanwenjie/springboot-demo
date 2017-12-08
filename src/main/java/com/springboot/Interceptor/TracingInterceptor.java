package com.springboot.Interceptor;

import org.aopalliance.intercept.Interceptor;
import org.apache.log4j.Logger;

public class TracingInterceptor implements Interceptor {
    private Logger logger = Logger.getLogger(TracingInterceptor.class);


    public void handle(){
        System.out.println("handle.....");
    }
}
