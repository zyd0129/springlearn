package com.zyd.learn.spring.springprinciple.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        AService aService = (AService) ctx.getBean("aService");
        BService bService = (BService) ctx.getBean("bService");
        Config config = (Config) ctx.getBean("config");
        System.out.println(aService);
    }
}
