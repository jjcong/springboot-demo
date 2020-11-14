package com.jincong.springboot.test.event.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * 
 * @author  j_cong
 * @date    2020/11/11
 * @version V1.0
 */
@Component
public class ContextClosedApplicationListener {
    @EventListener
    public void onContextClosedEvent(ContextClosedEvent event) {
        System.out.println("ContextClosedApplicationListener监听到ContextClosedEvent事件！");
    }
}
