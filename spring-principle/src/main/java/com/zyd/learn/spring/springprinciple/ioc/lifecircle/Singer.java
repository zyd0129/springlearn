package com.zyd.learn.spring.springprinciple.ioc.lifecircle;

import com.zyd.learn.spring.springprinciple.ioc.Company;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Scope("prototype")
public class Singer implements InitializingBean, DisposableBean, ApplicationContextAware {

    private Company company;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public void init() {
        System.out.println("init");
    }

    @Override
    public void destroy(){

    }

    @Autowired
    public void setCompany(Company company) {
        this.company = company;
        System.out.println("setter");
    }
}
