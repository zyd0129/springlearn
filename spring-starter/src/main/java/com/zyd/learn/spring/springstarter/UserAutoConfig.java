package com.zyd.learn.spring.springstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfig {
    @Autowired
    UserProperties userProperties;

    @Bean
    public UserClient userClient() {
        return new UserClient(userProperties);
    }
}
