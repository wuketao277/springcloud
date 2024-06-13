package com.hello.gateway.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author wuketao
 * @date 2019/9/29
 * @Description
 */
@Slf4j
@Configuration
public class GlobalFilterConfig {
    @Bean
    @Order(0)
    public GlobalFilter logFilter() {
        return (exchange, chain) -> {
            UUID uuid = UUID.randomUUID();
            try {
                ServerHttpRequest request = exchange.getRequest();
                log.info(String.format("log filter：%s；请求内容：%s",
                        uuid.toString(),
                        JSON.toJSONString(request)));
            } catch (Exception ex) {
                log.error(ex.toString());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                log.info(String.format("log filter：%s；返回结果：%s",
                        uuid.toString(),
                        JSON.toJSONString(response)));
            }));
        };
    }

    //    @Bean
//    @Order(0)
//    public GlobalFilter b() {
//        return (exchange, chain) -> {
//            log.info("second pre filter");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                log.info("second post filter");
//            }));
//        };
//    }
//
//    @Bean
//    @Order(1)
//    public GlobalFilter c() {
//        return (exchange, chain) -> {
//            log.info("third pre filter");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                log.info("first post filter");
//            }));
//        };
//    }
}
