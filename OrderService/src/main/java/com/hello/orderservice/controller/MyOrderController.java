package com.hello.orderservice.controller;

import com.hello.orderservice.feign.StorageFeign;
import com.hello.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author wuketao
 * @date 2024/6/13
 * @Description
 */
@RestController
@RequestMapping("order")
public class MyOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StorageFeign storageFeign;

    @GetMapping("getMsg")
    public String getMsg() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 3000; j++) {
                try {
                    FileWriter writer = new FileWriter("/Users/wuketao/Downloads", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write(".");
                    bufferedWriter.close();
                    writer.close();
                } catch (Exception e) {
                }
            }
        }
        return orderService.getMsg();
    }

    @GetMapping("getOrder")
    public String getOrder(String id) {
        return orderService.getOrder(id);
    }

    @GetMapping("getStorageAddress")
    public String getStorageAddress() {
        return storageFeign.getStorageAddress();
    }
}
