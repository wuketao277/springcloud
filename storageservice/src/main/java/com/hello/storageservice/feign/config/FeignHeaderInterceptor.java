package com.hello.storageservice.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Feign header拦截器
 */
public class FeignHeaderInterceptor implements RequestInterceptor {
    private static final String TRACE_ID_HEADER = "TraceId";

    @Override
    public void apply(RequestTemplate template) {
        long id = Thread.currentThread().getId();
        // 获取当前线程安全的traceId生成器
        String traceId = generateTraceId();
        // 将traceId添加到HTTP头中
        template.header(TRACE_ID_HEADER, traceId);
    }

    private String generateTraceId() {
        long id = Thread.currentThread().getId();
        String traceId = MDC.get("traceId");
        // 这里可以使用UUID或者分布式全局唯一ID生成器
        return traceId;//ThreadContext.containsKey("traceId") ? ThreadContext.get("traceId") : "traceId-" + UUID.randomUUID().toString();
    }
}
