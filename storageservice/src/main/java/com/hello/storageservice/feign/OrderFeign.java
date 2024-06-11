package com.hello.storageservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wuketao
 * @date 2024/6/7
 * @Description
 */
@FeignClient(value = "ORDER")
public interface OrderFeign {
    @GetMapping("/getOrder")
    String getOrder();
}
