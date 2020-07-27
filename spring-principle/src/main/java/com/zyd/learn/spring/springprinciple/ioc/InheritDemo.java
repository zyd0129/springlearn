package com.zyd.learn.spring.springprinciple.ioc;

import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;

class A {
    public void work() {
        System.out.println("this is A");
    }
}

class B extends A{
    public void work() {
        System.out.println("this is B");
    }
}

public class InheritDemo {
    public static void main(String[] args) {
        A a = new B();
        a.work(); //这里会调用B的方法，jvm是静态多分派（双分派），动态多分派
    }
}
