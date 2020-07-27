package learn.sc.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("ok")
    public String hello() {
//        int i = 1/0;
        return "ok" + port;
    }
}
