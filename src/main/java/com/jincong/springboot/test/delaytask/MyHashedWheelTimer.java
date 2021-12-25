package com.jincong.springboot.test.delaytask;

import io.netty.util.HashedWheelTimer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 使用时间轮实现延时任务
 * @author j_cong
 * @version V1.0
 * @date 2021/12/25
 */
@Slf4j
public class MyHashedWheelTimer {

    private static final long START_TIME = System.currentTimeMillis();

    public static void main(String[] args) {
        // tickDuration 时间间隔
        // ticksPerWheel 时间轮槽数
        HashedWheelTimer timer = new HashedWheelTimer(1, TimeUnit.SECONDS, 10);

        TimerTask task1 = timeout -> log.info("已经过了{}秒， task1开始执行。。。。。。。。。。。。。。。", costTime());
        TimerTask task2 = timeout -> log.info("已经过了{}秒， task2开始执行。。。。。。。。。。。。。。。", costTime());
        TimerTask task3 = timeout -> log.info("已经过了{}秒， task3开始执行。。。。。。。。。。。。。。。", costTime());

        timer.newTimeout(task1, 0, TimeUnit.SECONDS);
        timer.newTimeout(task2, 3, TimeUnit.SECONDS);
        timer.newTimeout(task3, 10, TimeUnit.SECONDS);

    }


    private static long costTime() {
        return (System.currentTimeMillis() - START_TIME) / 1000;
    }
}
