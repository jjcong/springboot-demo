package com.jincong.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author j_cong
 * @version V1.0
 * @date 2022/4/23
 */
@Component
@Slf4j
public class AutoJobService {

    @Scheduled(cron = "*/5 * * * * ?")
    public void cron() {
        log.info("执行定时任务。。。。。。。");
    }
}
