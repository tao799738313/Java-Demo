package pers.hmm.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @Author: hmm
 * @Created: 2019/10/17
 * @Description:
 * @Modified by:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class);
    }
}
