package com.pdt.moduleorder9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ModuleOrder9001Application {

    public static void main(String[] args) {
        SpringApplication.run(ModuleOrder9001Application.class, args);
    }

}
