package com.zyd.learn.spring.springprinciple.ioc.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.publishEvent(new ApplicationEvent("hello") {
        });

    }
}
