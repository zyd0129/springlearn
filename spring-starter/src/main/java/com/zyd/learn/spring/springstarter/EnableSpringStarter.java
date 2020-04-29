package com.zyd.learn.spring.springstarter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented // 注解的内容会被javadoc收集
@Inherited
@Import(UserAutoConfig.class)
public @interface EnableSpringStarter {
}
