package com.zyd.learn.spring.springstarter;

public class UserClient {
    UserProperties userProperties;

    public UserClient(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    public UserProperties getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(UserProperties userProperties) {
        this.userProperties = userProperties;
    }
    public String getName() {
        return userProperties.getName();
    }
}
