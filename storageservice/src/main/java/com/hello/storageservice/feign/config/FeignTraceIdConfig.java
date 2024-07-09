package com.hello.storageservice.feign.config;

import feign.Feign;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;

/**
 * Feign 客户端配置
 */
@Configuration
public class FeignTraceIdConfig {

    /**
     * 请求拦截
     *
     * @return
     */
    @Bean
    public Feign.Builder feignBuilder() {
        long id = Thread.currentThread().getId();
        return HystrixFeign.builder()
                .requestInterceptors(Arrays.asList(new FeignHeaderInterceptor()));
    }
}
