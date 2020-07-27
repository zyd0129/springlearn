package com.zyd.learn.spring.springprinciple.ioc;


public class DirectControlDependencies {
    static class Dependency{

    }
    private Dependency dependency;

    public DirectControlDependencies() {
        // direct control
        this.dependency = new Dependency();
    }
}
