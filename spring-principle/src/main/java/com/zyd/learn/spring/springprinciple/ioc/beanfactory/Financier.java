package com.zyd.learn.spring.springprinciple.ioc.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

public class Financier implements Life, ApplicationContextAware {
    @Value("#{fiancier.name}")
    private String name;

    private ApplicationContext context;

    @Override
    public void principle() {
        System.out.println(name);
        System.out.println("we get up at 6:00, work at 6:30");
    }

    @PostConstruct
    public void init(){
        System.out.println("postConstruct");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context =applicationContext;
        System.out.println("ApplicationContextAware");
    }
}
