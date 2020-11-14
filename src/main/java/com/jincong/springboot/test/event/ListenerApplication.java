package com.jincong.springboot.test.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 事件监听器启动类
 *
 * @author  j_cong
 * @date    2020/11/11
 * @version V1.0
 */
@Slf4j
public class ListenerApplication {

    public static void main(String[] args) {
        log.info("准备初始化容器。。。。");
        AnnotationConfigApplicationContext ctxt = new AnnotationConfigApplicationContext("com.jincong.springboot.test.event.listener");
        log.info("IOC容器初始化完成。。。。");
        ctxt.close();
        log.info("IOC容器关闭。。。。");
    }
}
