package com.zyd.learn.spring.springprinciple.ioc.lifecircle;

import com.zyd.learn.spring.springprinciple.ioc.Company;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
//@Import(MyImportBeanDefination.class)
public class Config {
    @Bean(initMethod = "init")
    public Singer singer() {
        return new Singer();
    }

    @Bean
    public Company company() {
        return new Company();
    }
}
