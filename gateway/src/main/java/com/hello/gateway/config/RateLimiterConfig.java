package com.hello.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流器配置
 * 按照客户ip地址限流
 * @author wuketao
 * @date 2019/10/26
 * @Description
 */
@Configuration
public class RateLimiterConfig {
    @Bean(value = "remoteAddKeyResolver")
    public KeyResolver remoteAddKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
