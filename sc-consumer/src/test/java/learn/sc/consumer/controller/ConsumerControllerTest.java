package learn.sc.consumer.controller;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;


public class ConsumerControllerTest {

    @Test
    public void testRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
//        String data = restTemplate.getForObject("http://localhost:8099/aa", String.class); //这个会抛出异常
        try {
            /**
             * 第三个参数variables是 get 参数，即url后面的参数
             * post 分为body数据 和 variables
             */
            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8099/ok", String.class);//这个也抛出异常
            String body = responseEntity.getBody();
            System.out.println(body);

        } catch (Exception e) {
            System.out.println(e);
        }
//        System.out.println(data);
    }
}