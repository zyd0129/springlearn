package learn.sc.consumer.controller;

import org.springframework.stereotype.Component;

@Component
public class ProviderServiceFallback implements ProviderService {
    @Override
    public String timeoutService() {
        return "timeout";
    }
}
