package com.zyd.learn.spring.springprinciple.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 底层是Config原理，
 * 1) ConfigurationClassPostProcessor对@Config注解的类进行BeanDefinition的修改，用cglib生成Config代理类,并替换config的BeanDefinition中的class属性
 * ConfigurationClassPostProcessor 会解析很多注解 @Configuration @Import等等
 * cglib代理示例，Enhancer，setSuperclass，setCallback（MethodInterceptor），create
 * public class SampleClass {
 *     public void test(){
 *         System.out.println("hello world");
 *     }
 *
 *     public static void main(String[] args) {
 *         Enhancer enhancer = new Enhancer();
 *         enhancer.setSuperclass(SampleClass.class);
 *         enhancer.setCallback(new MethodInterceptor() {
 *             @Override
 *             public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
 *                 System.out.println("before method run...");
 *                 Object result = proxy.invokeSuper(obj, args);
 *                 System.out.println("after method run...");
 *                 return result;
 *             }
 *         });
 *         SampleClass sample = (SampleClass) enhancer.create();
 *         sample.test();
 *     }
 * }
 *
 * 2）@Bean解析
 *  * <!--生成对象的工厂-->
 *  *     <bean id="stuFactory" class="com.dragon.study.study20190618.spring.springFactoryMethod.StuFactory"/>
 *  *     <!--动态获取对象-->
 *  *     <bean id="aService" factory-bean="config" factory-method="aService">
 *  *         <!--传入getDynamicStu方法的参数-->
 *  *         <constructor-arg value="11"/>
 *  *     </bean>
 *
 * 这里会调用代理对象的方法， 代理对象的拦截思路是，先从BeanFactory中获取，获取不到，则调用原始类的方法创建对象，放入单例池中。
 * 虽然这里是内部方法调用，但是代理不会失效，因为对于没有接口的类，使用cglib代理，是继承关系。
 * 针对事务，是因为我们大多数情况下是对由接口的service代理，所以就是jdk代理，由于jdk代理和原生对象是组合关系，所以内部调用机会失效
 * 另外事务只会针对public方法生成代理，这也是由于代理的盲区导致，私有方法就是代理盲区
 */
@Configuration
public class Config {
    @Bean
    public AService aService() {
        return new AService();
    }

    @Bean
    public BService bService(){
        /**
         * aService() 被代理对象重写，
         */
        return new BService(aService());
    }
}
