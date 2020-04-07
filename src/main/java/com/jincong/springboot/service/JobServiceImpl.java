package com.jincong.springboot.service;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JobServiceImpl implements JobService {
    @Override
    //@Scheduled(cron = "*/5 * * * * ?")
    public void timerToNow() {
        System.out.println("Now time: " +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
