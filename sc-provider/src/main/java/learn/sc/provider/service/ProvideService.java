package learn.sc.provider.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class ProvideService {

    @HystrixCommand(fallbackMethod = "timeoutService_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "100000"),//窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//错误率
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
}
