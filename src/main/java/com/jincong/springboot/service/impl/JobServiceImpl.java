package com.jincong.springboot.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jincong.springboot.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JobServiceImpl implements JobService {

   ExecutorService threadPool  =
           new ThreadPoolExecutor(10,
                   100,
                   60,
                   TimeUnit.SECONDS,
                   new ArrayBlockingQueue<>(100),
                    new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(),
                   new ThreadPoolExecutor.CallerRunsPolicy());


    private static Map<String, Object> map = new HashMap<>();

    @Override
    // @Scheduled(cron = "*/2 * * * * ?")
    public void timerToNow() {

        byte[] obj = new byte[1024 * 1024 * 512];



        int count = 50000;
        for (int i = 0; i < count; i++) {
            int index = i;
            threadPool.execute(()-> {
                String name = index +  "#" + Thread.currentThread().getName()+ System.currentTimeMillis();
                map.put(name, obj);
                log.info("start work, {}, mapSize = {}}", name, map.size());
                    }
            );
        }
    }
}
