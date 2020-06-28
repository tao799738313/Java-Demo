package pers.hmm.shop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author zclever<304078606@ qq.com>
 * @date 2019/5/30
 */
@Component
public class AddOpenidFilterFactory extends AbstractGatewayFilterFactory {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            URI uri = exchange.getRequest().getURI();
            StringBuilder query = new StringBuilder();
            String originalQuery = uri.getRawQuery();

            if (StringUtils.hasText(originalQuery)) {
                query.append(originalQuery);
                if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                    query.append('&');
                }
            }

            String jwtToken = exchange.getRequest().getHeaders().getFirst("token");
            //解析openid并转发给后端应用
            String openid = null;//TODO JWTService.getLoginId(jwtToken);
            logger.info("获取用户openid：{}", openid);
            query.append("input.openid");
            query.append('=');
            query.append(openid);
            try {
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(query.toString())
                        .build(true)
                        .toUri();

                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();

                return chain.filter(exchange.mutate().request(request).build());
            } catch (RuntimeException ex) {
                throw new IllegalStateException("Invalid URI query: \"" + query.toString() + "\"");
            }
        };
    }
}