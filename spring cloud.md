## 微服务架构

微服务，单独进程，独立部署，可以单独数据库，可以不同语言开发，足够内聚，小团队开发。

微服务框架，是把微服务整合起来，提高完整的服务；

![这里写图片描述](/Users/yudong/learn/alibaba/java/imgs/701.png)

服务注册与发现 服务调用 服务荣孤单 服务降级 

负载均衡 服务网关 配置中心 服务监控 全链路追踪

消息队列 自动化构建部署 服务定时任务调度操作

服务注册中心：**eureka**,**zookeeper**,consul(go),**nacos**

服务负责均衡：ribbon,loadBalancer

服务调用： feign(X) openFeign

服务降级： Hystrix(X) resilience4j sentinel

服务网关：zuul **gateway**

服务配置： config(X)  apolo（携程)  **nacos**

服务总线： bus **nacos**



**单体架构 和 微服务架构**

单体架构，所有模块打包成一个jar包，共享一个Jvm；一个模块挂了，程序就挂了

链路追踪，为了追查bug 在哪个服务发生；

服务雪崩，有了熔断降级处理

服务治理，管理服务的注册、续约、剔除等。

微服务架构，整个系统有若干独立的微服务构成，服务之间通过远程调用。

## CAP理论

![img](https://www.wangbase.com/blogimg/asset/201807/bg2018071607.jpg)

分区容错：因为网络存在抖动，区间通信可能失败；因此分区容错一定成立，除非单台机子；就不是分布式了。

![img](https://www.wangbase.com/blogimg/asset/201807/bg2018071601.png)

一致性：

写入G1，v1；那么从同一时间从G2读到的也是v1；

可用性：

Availability 中文叫做"可用性"，意思是只要收到用户的请求，服务器就必须给出回应。

### Consistency 和 Availability 的矛盾

一致性和可用性，为什么不可能同时成立？答案很简单，因为可能通信失败（即出现分区容错）。

如果保证 G2 的一致性，那么 G1 必须在写操作时，锁定 G2 的读操作和写操作。只有数据同步后，才能重新开放读写。锁定期间，G2 不能读写，没有可用性不。

如果保证 G2 的可用性，那么势必不能锁定 G2，所以一致性不成立。

综上所述，G2 无法同时做到一致性和可用性。系统设计时只能选择一个目标。如果追求一致性，那么无法保证所有节点的可用性；如果追求所有节点的可用性，那就没法做到一致性。

## 服务注册/服务治理

Eureka 采用cs的设计架构，存在一个Eureka Server,即注册中心；其它服务是客户端，连接到server并维持心跳。

客户端从server获取其它服务信息，然后直接发起调用

Eureka是AP理论， 有保护机制，节点删除之后，不会把它的注册信息立马删除掉

默认情况下，如果EurekaServer再一定时间内没有接受到某个微服务实例的心跳，server将会注销该实例（默认90s）；但是当网络分区故障发生（延迟、卡顿、拥挤）时，微服务和EurekaServer之间无法正常通行，以上行为可能变得非常危险--因为微服务本身其实时健康的，此时本不应该注销这个微服务。Eureka通过“自我保护模式" 来解决这个问题。当EurekaServer节点再短时间内丢失过多客户端时，（可能发生了网络分区故障），那么这个节点就会进入自我保护模式，宁可放过一千，不可错杀一个。

```yml
enable-self-preservation: false  //关闭自我保护
```

```
lease-renewal-interval-in-seconds: 1 //客户端设置心跳间隔
lease-expiration-duration-in-seconds: 2 //收到心跳2s后没有收到心跳，即剔除
```

#### 为什么需要注册中心？为什么不用nginx

硬编码的问题

![image-20200727232358345](/Users/yudong/learn/alibaba/java/imgs/image-20200727232358345.png)

1.硬编码调用rest，或者手动维护一个注册表（这个相当于Nginx了）

2.如果做服务集群，扩容的时候都需要手动修改；无法**动态感知**，人工干预效率低

#### 注册中心功能

提供注册接口、服务获取接口、心跳检测接口、取消注册接口

1.动态感知，服务A挂掉了，要剔除 服务器端起一个 服务端要起一个定时任务

2.如果注册中心挂了呢？客户端缓存一份列表 客户端与注册中心数据同步问题？客户端也要有一个定时任务，每隔30s拉取一次

总结一下：

服务端：

- 服务注册
- 服务发现接口
- 心跳检测
- 定时任务，清除不可用服务

客户端：

- 缓存服务列表
- 定时任务，重新拉取服务列表
- 注册
- 心跳

#### Eureka 集群架构

![image-20200727234358954](/Users/yudong/learn/alibaba/java/imgs/image-20200727234358954.png)

每个EurekaServer可以在不同的地区，实现高可用。可以选两个地区，避免断电。

https://www.cnblogs.com/shihaiming/p/11590748.html

https://www.cnblogs.com/sky-chen/p/11357681.html

Eureka 源码精华：

1.自动发现+Enable...（ConditonOnBean(...Mark.class)

2.registry数据存储 + readAndWriteCache(对外输出结构) + readOnlyCache

3.定时任务： 一个调度任务 里 去启动 调度任务，实现定期执行；如果这次超时，会延长调度delay时间，降低调度次数；如果在限定时间内完成，则重置delay时间。

- 一个ScheduledExecutorService,一个ThreadPoolExecutor
- future模式

```java
public void run() {
        Future<?> future = null;
        try {
            future = executor.submit(task);
            threadPoolLevelGauge.set((long) executor.getActiveCount());
            future.get(timeoutMillis, TimeUnit.MILLISECONDS);  // block until done or timeout
            delay.set(timeoutMillis);
            threadPoolLevelGauge.set((long) executor.getActiveCount());
            successCounter.increment();
        } catch (TimeoutException e) {
            logger.warn("task supervisor timed out", e);
            timeoutCounter.increment();

            long currentDelay = delay.get();
            long newDelay = Math.min(maxDelay, currentDelay * 2);
            delay.compareAndSet(currentDelay, newDelay);

        } catch (RejectedExecutionException e) {
            if (executor.isShutdown() || scheduler.isShutdown()) {
                logger.warn("task supervisor shutting down, reject the task", e);
            } else {
                logger.warn("task supervisor rejected the task", e);
            }

            rejectedCounter.increment();
        } catch (Throwable e) {
            if (executor.isShutdown() || scheduler.isShutdown()) {
                logger.warn("task supervisor shutting down, can't accept the task");
            } else {
                logger.warn("task supervisor threw an exception", e);
            }

            throwableCounter.increment();
        } finally {
            if (future != null) {
                future.cancel(true);
            }

            if (!scheduler.isShutdown()) {
                scheduler.schedule(this, delay.get(), TimeUnit.MILLISECONDS);
            }
        }
    }
```





## ribbon

ribbon客户端负载均衡； nginx是服务端负载均衡

spring cloud 注册客户端starter自动集成ribbon

负载均衡 就是 ribbon+restTemplate +@LoadBalance

restTemplate  拦截机制、错误处理、消息转换等等。 默认status不是200,抛出异常

负载均衡规则：

1.RandomRule

2.RetryRule

3.RoundRobinRule

4.AvailableFilterRule 过滤掉熔断的

自旋锁： 原子类+ CAS自旋

ribbon有一个ping机制，先从Eureka本地缓存中取



## openFeign

openFeign=ribbon+restTemplate

@EnableFeignClient(basePackages=)

@FeignClient

配置超时时间，实际是配置ribbon的超时时间

日志级别：None,BASIC,HEADERS,FULL

我们在使用Http请求时，或多或少都会使用到拦截器，那么在 FeignClient 定义拦截器怎么定义呢？两种方式.

**1、继承 RequestInterceptor 类**

```java
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component; 
@Component
public class TestInterceptor implements RequestInterceptor 
{
@Override
public void apply(RequestTemplate template) {//拦截器的处理逻辑}}
```

这种方式声明的拦截器是全局的，也就是所有的 FeignClient 发出的请求都会走这个拦截器。

在某一些情况下，我们只需要拦截部分特定的url，也就是为每一个FeignClient单独设置拦截器，那么你可以使用第二种方式

**2、通过 FeignClient 中的 configuration 属性，设置单独的配置类（里边可以设置很多相关的配置）**

TestFeignClient

指定的配置类是 TestConfig

```
@FeignClient(configuration = TestConfig.class)
public class TestFeignClient {``}
```

TestConfig的定义如下，

```java
public class TestConfig {
    @Bean public TestInterceptor testInterceptor(){
        return new TestInterceptor();}}
注意，这里不能对 TestConfig 添加 ``@Configuration``，咱们看下官方的说明：
```

### hystrix

服务熔断、降级，避免故障在整个分布式系统总的蔓延，造成雪崩

单服务多个接口，一个接口如果访问压力过大、响应过慢，会拖垮其它接口。tomcat的默认工作线程数被打满了，没有多余的线程来分解压力和处理。主要原因资源共享。

在这个类HystrixCommandProperties查看属性配置。

hystrix是单独的线程池，区别tomcat的线程池

服务降级:超时异常、计算异常都有兜底方案；一般服务降级放在客户端

```java
 @HystrixCommand(fallbackMethod = "timeoutService_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeoutService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "当前线程:" + Thread.currentThread().getId();
    }

    /**
     * 方法签名除了方法名，其它一样
     *
     * @return
     */
    public String timeoutService_handler() {
        return "timeout";
    }
```

全局fallback函数

服务熔断：熔断机制是应对雪崩效应的一种微服务链路保护机制，当某个微服务出错不可用或者响应时间太长会进行服务的降级，进而熔断该节点微服务的调用。

比如，当失败的调用到一定阈值，默认是是5s2次调用失败，就会启动熔断机制。

当检测到该节点微服务调用响应正常后，恢复调用链路。

断路器三种状态：开、闭、半开 断路器开，无法访问

先降级、熔断、恢复

![image-20200730151443284](D:\learn\learn-mybatis\img\image-20200730151443284.png)

gateway:采用新一代技术webflux,webflux,reactor,底层是基于netty，可以支持高并发

zuul1维护，zuul2没有定论，gateway是spring cloud自己开发

zuul1 是 基于阻塞io

spring cloud gateway 是异步非阻塞



## 配置中心

## 消息总线

所有微服务监听同一个主题

1.配置自动更新

2.权限

### Nacos

配置中心，自带嵌入式数据库，是无法做集群的；目前只支持mysql

可以在CP和AP切换

nacos-server nacos-client spring cloud, spring cloud alibaba

分布式一致性算法：

https://zhuanlan.zhihu.com/p/130332285

servlet异步、长轮询

## 1.1常见的Web端实时通讯技术的实现方式

### 1.1.1短轮询

客户端每隔一段时间向服务端发送请求,服务端收到请求即响应客户端请求,这种方式实现起来最简单,也比较实用,但缺点显而易见,实时性不高,而且频繁的请求在用户量过大时对服务器会造成很大压力.

### 1.1.2长轮询

服务端收到客户端发来的请求后不直接响应,而是将请求hold住一段时间,在这段时间内如果数据有变化,服务端才会响应,如果没有变化则在到达一定的时间后才返回请求,客户端Js处理完响应后会继续重发请求...这种方式能够大幅减少请求次数,减少服务端压力,同时能够增加响应的实时性,做的好的话基本上是即时响应的.

### 1.1.3长连接

长连接SSE是H5推出的新功能,全称Server-Sent Events,它可以允许服务推送数据到客户端,SSE不需要客户端向服务端发请求,服务端数据发生变化时,会主动向客户端发送,可以保证实时性,显著减轻服务端压力.

### 1.1.4websocket

https://blog.csdn.net/wangxindong11/article/details/78591396



看源码：

1.原理+算法+思路

2.源码主线+流程



### Stream

中间操作、终结操作

终结操作：非短路操作、短路操作（limit)

并行操作：parallel，原理(fork/join)

Stream源码：维护一个链表，和一个并行标志位

Stream.of([])

### Lambda表达式

非静态方法，有一个而外this参数

级联表达式 x->y->z, 返还一个lambda表达式

柯里化，将多元函数统一成单元函数