package com.hello.storageservice.feign;

import com.hello.storageservice.feign.config.FeignTraceIdConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuketao
 * @date 2024/6/7
 * @Description
 */
@Component
@FeignClient(name = "ORDER", path = "/order", fallback = OrderFeignFallback.class, configuration = FeignTraceIdConfig.class)
public interface OrderFeign {
    @GetMapping("/getOrder")
    String getOrder(@RequestParam("id") String id);

    @GetMapping("/getMsg")
    String getMsg();
}
