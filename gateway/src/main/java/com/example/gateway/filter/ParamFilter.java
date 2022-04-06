package com.example.gateway.filter;

import com.example.gateway.utils.RsaUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author 代志豪
 * 2021/12/28 16:52
 */
//@Component
public class ParamFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        URI uri = exchange.getRequest().getURI();
        String originalQuery = uri.getQuery();
        if (!StringUtils.isEmpty(originalQuery)) {
            String[] split = originalQuery.split("=");
            String decrypt = "";
            if (split.length == 2) {
                try {
                    decrypt = RsaUtil.decrypt(split[1], RsaUtil.getPrivateKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String newUrl = split[0] + "=" + decrypt;
            try {
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(newUrl).build(true).toUri();

                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri)
                        .build();

                return chain.filter(exchange.mutate().request(request).build());
            } catch (RuntimeException ex) {
                throw new IllegalStateException(
                        "Invalid URI query: \"" + newUrl + "\"");
            }
        } else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
