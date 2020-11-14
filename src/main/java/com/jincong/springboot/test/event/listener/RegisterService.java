package com.jincong.springboot.test.event.listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 用户注册服务
 *
 * @author  j_cong
 * @date    2020/11/11
 * @version V1.0
 */
@Service
public class RegisterService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    public void register(String userName) {
        System.out.println("用户" + userName + "注册成功！！！");
        publisher.publishEvent(new RegisterSuccessEvent(userName));

    }
}
