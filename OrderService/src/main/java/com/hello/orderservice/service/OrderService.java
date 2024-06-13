package com.hello.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wuketao
 * @date 2024/6/12
 */
@Slf4j
@Service
public class OrderService {
    public String getOrder(String id) {
        log.info(id + "查询订单");
        return id + "产品制作中...";
    }


    public String getMsg() {
        log.info("查询产品信息");
        return "产品信息";
    }
}
