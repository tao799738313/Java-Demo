package pers.hmm.shop.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: hmm
 * @Created: 2019/10/10
 * @Description:
 * @Modified by:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("pers.hmm.shop.manager.mapper")
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }
}
