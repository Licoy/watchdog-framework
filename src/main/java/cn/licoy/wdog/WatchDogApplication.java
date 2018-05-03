package cn.licoy.wdog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableCaching
public class WatchDogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchDogApplication.class, args);
    }
}
