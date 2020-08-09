package com.jincong.springboot.test.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用interrupt方法优雅的实现终止线程
 *
 * @author  j_cong
 * @date    2020/07/05
 * @version V1.0
 */
@Slf4j
public class TerminationThreadTest {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        log.info("测试测试");
        twoPhaseTermination.start();
        Thread.sleep(3500);
        twoPhaseTermination.stop();
    }

}
