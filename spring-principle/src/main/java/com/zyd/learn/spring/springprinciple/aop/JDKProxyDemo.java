package com.zyd.learn.spring.springprinciple.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IService{
    void info();
}

class Service implements IService {

    @Override
    public void info() {
        System.out.println("Info");
    }

    public void cus() {
        System.out.println("cus");
    }
}

public class JDKProxyDemo {
    public static void main(String[] args) {
        Service service = new Service();
        IService proxy = (Service) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy");
                return method.invoke(service, args);
            }
        });
        proxy.info();
    }
}
