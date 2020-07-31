package learn.sc.consumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    /**
     * 设置feign日志级别，full显示 head + body
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 默认是轮询
     * @return
     */
//    @Bean
//    public IRule customRule() {
//        return new RandomRule();
//    }
}
