package learn.sc.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider-service",fallback = ProviderServiceFallback.class)
public interface ProviderService {
    @GetMapping("timeoutService")
    public String timeoutService();
}
