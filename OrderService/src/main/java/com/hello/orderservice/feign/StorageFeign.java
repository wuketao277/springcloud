package com.hello.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 仓库服务的Feign接口
 */
@FeignClient(name = "STORAGE")
public interface StorageFeign {
    @GetMapping("/storage/getStorageAddress")
    String getStorageAddress();
}
