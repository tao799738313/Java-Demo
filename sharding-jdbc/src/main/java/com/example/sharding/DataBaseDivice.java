package com.example.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.sharding.service.DemoService;

//@SpringBootApplication()
//public class DataBaseDivice {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//
//}

@SpringBootApplication
public class DataBaseDivice {
	public static void main(final String[] args) {
		try (ConfigurableApplicationContext applicationContext = SpringApplication.run(DataBaseDivice.class, args)) {
			applicationContext.getBean(DemoService.class).demo();
		}
	}
}