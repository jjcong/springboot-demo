package com.jincong.springboot.controller;

import com.jincong.springboot.service.impl.CommonServiceImpl;
import com.jincong.springboot.test.DistributeLockWithRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.HelloService;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DemoController
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/1/9
 */
@RestController
@ResponseBody
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Autowired
    HelloService helloService;

    @Autowired
    private DistributeLockWithRedis redisLock;

    @Autowired
    private CommonServiceImpl commonService;



    @GetMapping("/hello")
    public String sayHello() {
        return helloService.sayHello("Hello");

    }

    @GetMapping("/redis")
    public String redis() {
        final int[] count = {0};

        int clientCount = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        DistributeLockWithRedis redisLock = new DistributeLockWithRedis();

        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);

        StopWatch stopWatch = new StopWatch("测试Redis分布式锁耗时");
        stopWatch.start();

        for (int i = 0; i < clientCount; i++) {
            executorService.execute(() -> {
                String uid = UUID.randomUUID().toString();
                try {
                    redisLock.lock(uid);
                    count[0]++;
                } finally {
                    redisLock.unlock(uid);
                }
                countDownLatch.countDown();
            });
        }
        stopWatch.stop();
        log.info("总耗时 {}", stopWatch.prettyPrint());
        log.info("最终结果， count= {}", Arrays.toString(count));

        return "";

    }


    @GetMapping("/testTransaction")
    public void testTransaction() {
        commonService.transfer(1001, 1002, 100);

    }

}
