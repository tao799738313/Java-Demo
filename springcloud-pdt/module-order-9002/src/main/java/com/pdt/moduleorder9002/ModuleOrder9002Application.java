package com.pdt.moduleorder9002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ModuleOrder9002Application {

    public static void main(String[] args) {
        SpringApplication.run(ModuleOrder9002Application.class, args);
    }

}
