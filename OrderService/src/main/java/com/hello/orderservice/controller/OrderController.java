package com.hello.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wuketao
 * @date 2024/6/7
 * @Description
 */
@RestController("OrderController")
public class OrderController {
    @GetMapping("getOrder")
    public String getOrder() {
        System.out.println(new Date() + " getOrder");
        return "test";
    }
}
