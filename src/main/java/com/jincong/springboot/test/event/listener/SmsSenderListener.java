package com.jincong.springboot.test.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 短信服务监听器
 * 
 * @author  j_cong
 * @date    2020/11/11
 * @version V1.0
 */
@Component
public class SmsSenderListener implements ApplicationListener<RegisterSuccessEvent> {
    @Override
    @Order(3)
    public void onApplicationEvent(RegisterSuccessEvent event) {
        System.out.println("监听到用户注册成功，发送短信。。。");
    }
}
