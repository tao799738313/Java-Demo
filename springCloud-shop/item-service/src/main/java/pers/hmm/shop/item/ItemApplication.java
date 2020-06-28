package pers.hmm.shop.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author humin
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@MapperScan("pers.hmm.shop.item.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class);
    }
}
