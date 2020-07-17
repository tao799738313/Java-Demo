package com.pdt.gateway80;


import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

@Configuration
public class MyGatewayFilter {

    @Bean
    public RouteLocator OrderRouteLocator(RouteLocatorBuilder builder) {
        String url = "http://www.baidu.com";
        RouteLocator build = builder.routes()
                .route(r ->
                        r.path("/ORDER/**")
                                .and().method(HttpMethod.GET)
                        .filters(f -> f.filter(new OrderFilter()))
                        .uri(url)
                )
                .build();
        return build;
    }


//    @Bean
//    @Order(-1)
//    public GlobalFilter a() {
//        return (exchange, chain) -> {
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            }));
//        };
//    }
//    @Bean
//    @Order(0)
//    public GlobalFilter b() {
//        return (exchange, chain) -> {
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            }));
//        };
//    }
//    @Bean
//    @Order(1)
//    public GlobalFilter c() {
//        return (exchange, chain) -> {
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            }));
//        };
//    }
}