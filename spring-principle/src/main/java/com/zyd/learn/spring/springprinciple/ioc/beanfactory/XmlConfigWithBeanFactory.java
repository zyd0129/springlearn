package com.zyd.learn.spring.springprinciple.ioc.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class XmlConfigWithBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        Life a = (Life) beanFactory.getBean("life");
        System.out.println(a);

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(Financier.class);
        beanDefinition.setScope("singleton");
        beanFactory.registerBeanDefinition("life2", beanDefinition);

        Life b = beanFactory.getBean("life2", Life.class);
        System.out.println(b);

    }
}
