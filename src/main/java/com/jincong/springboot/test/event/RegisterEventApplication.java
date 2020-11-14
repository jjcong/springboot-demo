package com.jincong.springboot.test.event;

import com.jincong.springboot.test.event.listener.RegisterService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RegisterEventApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.jincong.springboot.test.event");
        RegisterService registerService = ctx.getBean(RegisterService.class);
        registerService.register("张无忌");
    }
}
