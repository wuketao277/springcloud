package com.hello.orderservice.controller;

import com.hello.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("getMsg")
    public String getMsg(){
        return orderService.getMsg();
    }

    @GetMapping("getOrder")
    public String getOrder(String id){
        return orderService.getOrder(id);
    }
}
