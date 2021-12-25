package com.jincong.springboot.test.delaytask;

import cn.hutool.core.collection.CollectionUtil;
import com.jincong.springboot.service.RedisTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 使用redis的ZSet实现延迟任务
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/12/25
 */
@Slf4j
@Service
public class MyZSetTask {

    @Autowired
    RedisTemplateService redisTemplateService;


    public MyZSetTask(RedisTemplateService redisTemplateService) {
        this.redisTemplateService = redisTemplateService;
    }

    /**
     * ZSET的key
     */
    public static final String DELAY_QUEUE = "DELAY_QUEUE";


    public void addDelayTasks(long delayTime) {
        String orderId = "TASK-" + delayTime / 1000;
        Boolean addResult = redisTemplateService.addZset(DELAY_QUEUE, orderId, (System.currentTimeMillis() + delayTime) * 1.0);
        if (Boolean.TRUE.equals(addResult)) {
            log.info("添加任务成功，orderId = {}， 当前时间：{}", orderId, LocalDateTime.now());
        }
    }


    public void listenDelayLoop() {

        while (true) {
            Set<String> set = redisTemplateService.range(DELAY_QUEUE, 0, System.currentTimeMillis());
            if (CollectionUtil.isEmpty(set)) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            String task = set.iterator().next();
            if (redisTemplateService.deleteZset(DELAY_QUEUE, task) > 0) {
                log.info("消息到期， task= {}， 删除时间： {}", task, LocalDateTime.now());
            }
        }
    }

}
