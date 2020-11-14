package com.jincong.springboot.test.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 邮件服务监听器
 * Order注解标识事件的优先级，值越小优先级越高
 * 
 * @author  j_cong
 * @date    2020/11/11
 * @version V1.0
 */
@Component
public class MessageSenderListener implements ApplicationListener<RegisterSuccessEvent> {
    @Override
    @Order(1)
    public void onApplicationEvent(RegisterSuccessEvent event) {
        System.out.println("监听到用户注册成功，发送站内信。。。");
    }
}
