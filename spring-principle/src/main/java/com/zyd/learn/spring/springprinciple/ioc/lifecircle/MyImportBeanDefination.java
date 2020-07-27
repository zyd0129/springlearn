package com.zyd.learn.spring.springprinciple.ioc.lifecircle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefination implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinition beanDefinition = new RootBeanDefinition(User.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(1);
        beanDefinitionRegistry.registerBeanDefinition("user", beanDefinition);
    }

    public static class User{
        public User() {
        }
        public User(int i) {
        }

    }
}
