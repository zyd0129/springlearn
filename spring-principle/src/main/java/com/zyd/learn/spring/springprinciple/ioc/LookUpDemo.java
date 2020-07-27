package com.zyd.learn.spring.springprinciple.ioc;

import com.zyd.learn.spring.springprinciple.ioc.lifecircle.Singer;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class LookUpDemo {
    /**
     * 动态生成一个代理类 继承LookUpDemo
     * @return
     */
    @Lookup("singer")
    public Singer getMySinger() {
        return null;
    }
    public void doSomething(){
        System.out.println(getMySinger().hashCode());
    }
}
