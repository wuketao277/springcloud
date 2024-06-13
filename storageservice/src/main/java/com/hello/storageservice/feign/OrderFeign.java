package com.hello.storageservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuketao
 * @date 2024/6/7
 * @Description
 */
@Component
@FeignClient(value = "ORDER")
public interface OrderFeign {
    @GetMapping("/getOrder")
    String getOrder(@RequestParam("id") String id);

    @GetMapping("/getMsg")
    String getMsg();
}
