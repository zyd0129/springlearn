starter的作用是，引入外部项目的 configuration,自动装载一些bean.
引入外部配置的方式：
1.在META-INF/spring.factories 添加 自动配置类
2.使用@Enable*** 注解，@Enable**注解 @import配置类