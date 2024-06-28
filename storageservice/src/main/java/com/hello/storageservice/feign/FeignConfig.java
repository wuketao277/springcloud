package com.hello.storageservice.feign;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FeignConfig {
    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(10000, 10000); // 连接超时5秒，读取超时5秒
    }
}
