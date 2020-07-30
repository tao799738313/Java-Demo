package com.pdt.moduleorder9002;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.pdt.moduleorder9002.mapper")
public class ModuleOrder9002Application {

    public static void main(String[] args) {
        SpringApplication.run(ModuleOrder9002Application.class, args);
    }

}
