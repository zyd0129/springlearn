package com.zyd.learn.spring.springprinciple;

import com.zyd.learn.spring.springstarter.UserClient;
import com.zyd.learn.spring.springstarter.components.C1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自动装载原理  @EnableAutoConfig 导入了一个@Import(AutoConfigurationImportSelector.class), 这是一个实现了ImportSelector接口的导入
 * 这个类会返还 一个类全限定名数组，会讲他们放入容器。 字符串数组会扫描所有包下WEB-INFO下的spring.factory下的EnableAutoConfiguration的值
 */
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
