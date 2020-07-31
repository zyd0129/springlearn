package learn.sc.provider.controller;

import learn.sc.provider.service.ProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @Autowired
    ProvideService provideService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("ok")
    public String hello() {
//        int i = 1/0;
        return "ok" + port;
    }

    @GetMapping("timeoutService")
    public String timeoutService() {

        return provideService.timeoutService();
    }
}
