package com.jincong.springboot.test.event.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 用户注册成功事件
 * 
 * @author  j_cong
 * @date    2020/11/11
 * @version V1.0
 */
public class RegisterSuccessEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1267248765752622481L;

    public RegisterSuccessEvent(Object source) {
        super(source);
    }
}
