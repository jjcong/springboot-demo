package com.jincong.springboot.test.delaytask;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 使用JDK提供的延时队列实现延时任务
 * 优点：
 *  1) 效率高，任务延迟低
 *  缺点：
 *  1) 服务重启后，数据全部丢失
 *  2) 集群拓展相当麻烦
 *  3) 内部为无界队列，如果任务数过多可能引发OOM
 *  4) 代码复杂度较高
 * @author j_cong
 * @version V1.0
 * @date 2021/12/25
 */
@Slf4j
public class MyDelayQueue implements Delayed {


    private String orderId;

    private long startTime;


    private long delayTime;

    public MyDelayQueue(String orderId, long delayTime) {
        this.orderId = orderId;
        this.delayTime = delayTime;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert((startTime + delayTime) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }


    public void exec() {
        log.info("订单号= {}被删除了。。。。。。。。。。。。。。。。。", orderId);
    }


    public static void main(String[] args) {
        List<String> orderList = Lists.newArrayList();
        orderList.add("00000000001");
        orderList.add("00000000002");
        orderList.add("00000000003");
        orderList.add("00000000004");
        orderList.add("00000000005");

        DelayQueue<MyDelayQueue> delayQueue = new DelayQueue<>();
        long startTime = System.currentTimeMillis();

        orderList.forEach(order -> {
            delayQueue.add(new MyDelayQueue(order, 3000));
            try {
                delayQueue.take().exec();
                log.info("执行任务，time= {} ms", (System.currentTimeMillis() - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
