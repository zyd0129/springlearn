package com.zyd.learn.spring.springprinciple.annotation;

import java.lang.annotation.Annotation;

public class ADemo {
    public static void main(String[] args) {
        Annotation[] annotations = A.class.getDeclaredAnnotations();
        System.out.println(annotations);
    }
}
