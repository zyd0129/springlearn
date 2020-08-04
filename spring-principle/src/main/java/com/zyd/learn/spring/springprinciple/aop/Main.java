package com.zyd.learn.spring.springprinciple.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.*;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        AService a = ctx.getBean(AService.class);
        a.service();
    }

    @Bean
    public AService aService() {
        return new AService();
    }

//    @Bean
//    public MyAspect myAspect() {
//        return new MyAspect();
//    }
}
