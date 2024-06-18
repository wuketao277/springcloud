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
        log.info("定时任务");
    }
}
