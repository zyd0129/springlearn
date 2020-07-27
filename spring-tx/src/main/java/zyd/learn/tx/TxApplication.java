package zyd.learn.tx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("zyd.learn.tx.dao.entity")
public class TxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class, args);
    }
}
