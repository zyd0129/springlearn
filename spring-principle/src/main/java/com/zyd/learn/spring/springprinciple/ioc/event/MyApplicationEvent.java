package com.zyd.learn.spring.springprinciple.ioc.event;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableListableBeanFactory.SCOPE_PROTOTYPE,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyApplicationEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
