package com.hello.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wuketao
 * @date 2019/10/26
 */
@RestController
public class SelfHystrixController {

    @RequestMapping("/defaultfallback")
    public String defaultfallback() {
        return "服务熔断";
    }
}
