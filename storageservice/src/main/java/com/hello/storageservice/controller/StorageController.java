package com.hello.storageservice.controller;

import com.hello.storageservice.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuketao
 * @date 2024/6/7
 * @Description
 */
@RestController("storage")
public class StorageController {
    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("getStorage")
    public String getStorage(String id) {
        return orderFeign.getOrder(id);
    }

    @GetMapping("getMsg")
    public String getMsg(){
        return orderFeign.getMsg();
    }

}
