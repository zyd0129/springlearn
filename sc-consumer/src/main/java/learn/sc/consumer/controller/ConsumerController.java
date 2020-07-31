package learn.sc.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
//@DefaultProperties(defaultFallback = "") 全局fallback,只对该类内部有效
public class ConsumerController {
//    private static final String PAYMENT_URL = "http://localhost:9001";
    private static final String PAYMENT_URL = "http://PROVIDER-SERVICE";
//    @Resource
//    RestTemplate restTemplate;

    @Autowired
    ProviderService providerService;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("ok")
    public String hello() {
        return "ok";
    }

//    @GetMapping("/consumer/payment/create")
//    public String create() {
//        return restTemplate.getForObject(PAYMENT_URL + "/ok", String.class);
//    }

    @GetMapping("/consumer/provider/timeoutService")
    public String providerTimeoutService() {
        return providerService.timeoutService();
    }

    @GetMapping("discovery")
    public List<String> discovery() {
        List<String> services = discoveryClient.getServices();

        List<ServiceInstance> instances = discoveryClient.getInstances("provider-service");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost() + ":" + instance.getPort() );
        }
        return services;
    }
}
