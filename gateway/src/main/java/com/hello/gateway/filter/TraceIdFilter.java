package com.hello.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.core.Ordered;

import java.util.UUID;

/**
 * 网关traceId过滤器，给请求头中增加TraceId.
 */
@Component
public class TraceIdFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -3;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 检查如果请求head中没有TraceId，就给head中添加一个。
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        if (!serverHttpRequest.getHeaders().containsKey("TraceId")) {
            serverHttpRequest = exchange.getRequest().mutate()
                    .headers(headers -> headers.set("TraceId", generateTraceId()))
                    .build();
        }
        return chain.filter(exchange.mutate().request(serverHttpRequest).build());
    }

    private String generateTraceId() {
        return "traceId-" + UUID.randomUUID().toString();
    }
}
