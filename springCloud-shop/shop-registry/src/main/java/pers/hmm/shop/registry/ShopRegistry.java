package pers.hmm.shop.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ShopRegistry {
    public static void main(String[] args) {
        SpringApplication.run(ShopRegistry.class);
    }
}
