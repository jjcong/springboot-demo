package com.jincong.springboot.test;

import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author j_cong
 * @version V1.0
 * @date 2022/1/9
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args) {

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        System.out.println(threadLocalRandom.nextInt(10));


        StopWatch stopWatch = new StopWatch();
        stopWatch.start("计算时间1");

        Long sum1 = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 += i;
        }
        stopWatch.stop();

        stopWatch.start("计算时间2");

        long sum2 = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum2 += i;
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.prettyPrint());


        Map<Integer, Integer> map = new HashMap<>();

        System.out.println("map.putIfAbsent(1, 1) = " + map.putIfAbsent(1, 1));
        System.out.println("map.putIfAbsent(2, 2) = " + map.putIfAbsent(2, 2));
        System.out.println("map.putIfAbsent(1, 3) = " + map.putIfAbsent(1, 3));

    }
}
