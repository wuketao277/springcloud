package com.hello.orderservice.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author wuketao
 * @date 2024/6/13
 * @Description
 */
@Slf4j
@Component
public class MySchedule {

    @Scheduled(cron = "0 * * * * *")
    public void printTime() {
        // 定时任务向Redis中的list中push令牌。通过push令牌的速率限制请求数量。
        log.info("定时任务");
    }
}
