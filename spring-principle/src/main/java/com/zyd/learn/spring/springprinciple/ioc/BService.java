package com.zyd.learn.spring.springprinciple.ioc;


public class BService {
    private AService aService;

    public BService(AService aService) {
        this.aService = aService;
    }
}
