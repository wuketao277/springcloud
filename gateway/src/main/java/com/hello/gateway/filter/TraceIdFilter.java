package com.hello.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.core.Ordered;

import java.util.UUID;

@Component
public class TraceIdFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -3;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = generateTraceId();
        ServerHttpRequest request = exchange.getRequest().mutate()
                .headers(headers -> headers.set("TraceId", traceId))
                .build();
        System.out.println("网关添加traceId " + traceId);
        return chain.filter(exchange.mutate().request(request).build());
    }

    private String generateTraceId() {
        return "traceId-" + UUID.randomUUID().toString();
    }
}
