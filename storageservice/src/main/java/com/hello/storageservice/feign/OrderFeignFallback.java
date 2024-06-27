package com.hello.storageservice.feign;

import org.springframework.stereotype.Component;

/**
 * 降级处理类
 */
@Component
public class OrderFeignFallback implements OrderFeign {
    @Override
    public String getOrder(String id) {
        return id + "降级处理";
    }

    @Override
    public String getMsg() {
        return "降级处理";
    }
}
