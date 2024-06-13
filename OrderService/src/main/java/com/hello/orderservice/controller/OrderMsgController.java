package com.hello.orderservice.controller;

import com.hello.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuketao
 * @date 2024/6/13
 * @Description
 */
@RestController("order")
public class OrderMsgController {
    @Autowired
    private OrderService orderService;

    @GetMapping("getOrder")
    public String getOrder(String id) {
        return orderService.getOrder(id);
    }


    @GetMapping("getMsg")
    public String getMsg() {
        return orderService.getMsg();
    }
}
