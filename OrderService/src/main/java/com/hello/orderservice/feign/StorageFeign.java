package com.hello.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("STORAGE")
public interface StorageFeign {
    @GetMapping("getStorageAddress")
    String getStorageAddress();
}
