package com.zyd.learn.spring.springprinciple;

import com.zyd.learn.spring.springstarter.UserClient;
import com.zyd.learn.spring.springstarter.components.C1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringPrincipleApplication implements ApplicationRunner {
    @Autowired
    UserClient userClient;

    @Autowired
    C1 c1;

    public static void main(String[] args) {
        SpringApplication.run(SpringPrincipleApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(userClient.getName());
        System.out.println(c1);
    }
}
