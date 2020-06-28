package pers.hmm.shop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 开始使用的zuul最为网关
 * 后考虑gateway和zuul后 选择gateway作为网关
 * 此服务没用了
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class);
    }
}
