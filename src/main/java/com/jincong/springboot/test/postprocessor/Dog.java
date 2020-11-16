package com.jincong.springboot.test.postprocessor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Dog implements InitializingBean {
    public void initMethod() {
        System.out.println("initMethod ...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean ...");
    }
}
