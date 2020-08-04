package com.zyd.learn.spring.springprinciple.aop;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.Aspect;

@Component
@Aspect
public class MyAspect {
    @Pointcut("execution(public * *(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }
}
