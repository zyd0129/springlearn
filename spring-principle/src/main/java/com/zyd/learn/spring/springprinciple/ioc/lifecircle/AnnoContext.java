package com.zyd.learn.spring.springprinciple.ioc.lifecircle;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnoContext  implements CommandLineRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        ctx.getBean("singer");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
